package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.NewUserPost;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private DTOConverter dtoConverter;



    public PostService() throws IOException {
    }


    public void saveUserPostInfo(NewUserPost newUserPost, String userName, String postID) {
        UserPostModel userPostModel = userPostRepository.findByPostID(UUID.fromString(postID));

        UserAccountInfoModel accountInfoModel = userInfoRepository.findByUserName(userName);

        dtoConverter.convertDTO(userPostModel, newUserPost, accountInfoModel.getUserID(), accountInfoModel.getUserName());

        userPostRepository.save(userPostModel);
    }



    public String saveUserPostImages(MultipartFile image) throws IOException {
        UserPostModel userPostModel = new UserPostModel();

        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("Server/src/main/resources/keys.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        final String bucketName = "swap_image_store";


        List<String> gcsUrls = new ArrayList<>();

        String fileName = userPostModel.getPostID().toString() + "_" + UUID.randomUUID() + "_" + image.getOriginalFilename();
        String gcsObjectName = "Post_Images/" + fileName;

        storage.create(
                BlobInfo.newBuilder(bucketName, gcsObjectName).build()
        );

        gcsUrls.add(gcsObjectName);

        userPostModel.setGcsImages(gcsUrls);

        return userPostModel.getPostID().toString();
    }

    public void removeUserPost(String postID) {
        userPostRepository.deleteByPostID(UUID.fromString(postID));
    }
}
