package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Components.RemovingPosts;
import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.NewUserPost;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import com.google.auth.Credentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PostService {

    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private RemovingPosts removingPosts;

    private final Credentials credentials;
    private final Storage storage;
    private final String bucketName;

    @Autowired
    public PostService(Credentials credentials, Storage storage, String bucketName) throws IOException {
        this.credentials = credentials;
        this.storage = storage;
        this.bucketName = bucketName;
    }


    public void saveUserPostInfo(NewUserPost newUserPost, String userName, String postID) {
        UserPostModel userPostModel = userPostRepository.findByPostID(UUID.fromString(postID));

        UserAccountInfoModel accountInfoModel = userInfoRepository.findByUserName(userName);

        dtoConverter.convertDTO(userPostModel, newUserPost, accountInfoModel.getUserID(), accountInfoModel.getUserName());

        userPostRepository.save(userPostModel);
    }



    public String saveUserPostImages(MultipartFile image) throws IOException {
        UserPostModel userPostModel = new UserPostModel();

        String fileName = userPostModel.getPostID().toString() + "_" + UUID.randomUUID() + "_" + image.getOriginalFilename();
        String gcsObjectName = "Post_Images/" + fileName;

        byte[] imageBytes = image.getBytes();

        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, gcsObjectName).build(),
                imageBytes
        );

        userPostModel.getGcsObjectNames().add(blobInfo.getName());


        userPostRepository.save(userPostModel);

        return userPostModel.getPostID().toString();
    }


    public void removeUserPost(String postID) {
        UserPostModel userPostModel = userPostRepository.findByPostID(UUID.fromString(postID));

        // Delete Picture from GCS
        removingPosts.deleteFromGCS(userPostModel);

        removingPosts.removeOffersFromAllUsers(userPostModel, userPostModel.getUserName());

        userPostRepository.delete(userPostModel);
    }
}
