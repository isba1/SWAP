package com.SwapToSustain.Server.Converter;

import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.List;

@Component
public class DTOConverter {

    public void convertDTO(UserAccountInfoModel userAccountInfoModel, UserInterests userInterests){
        userAccountInfoModel.setInterestBrand(userInterests.getBrands());
        userAccountInfoModel.setInterestCategory(userInterests.getClothingCategory());
        userAccountInfoModel.setPantSize(userInterests.getPantSize());
        userAccountInfoModel.setShirtSize(userInterests.getShirtSize());
        userAccountInfoModel.setJacketSize(userInterests.getJacketSize());
        userAccountInfoModel.setShoeSize(userInterests.getShoeSize());
    }

    public void convertDTO(UserAccountInfoModel userAccountInfoModel, UserAccountInfo userAccountInfo) {
        userAccountInfoModel.setFullName(userAccountInfo.getFullName());
        userAccountInfoModel.setPassword(userAccountInfo.getPassword());
        userAccountInfoModel.setEmail(userAccountInfo.getEmail());
        userAccountInfoModel.setPhone(userAccountInfo.getPhone());
        userAccountInfoModel.setCity(userAccountInfo.getCity());
        userAccountInfoModel.setState(userAccountInfo.getState());
        userAccountInfoModel.setZipCode(userAccountInfo.getZipCode());
        userAccountInfoModel.setFollowers(new HashSet<>());
        userAccountInfoModel.setFollowing(new HashSet<>());
    }


    public void convertDTO(UserPostModel userPostModel, UserPost userPost, ObjectId objectId) {

        ArrayList<String> base64Images = new ArrayList<>(userPost.getBase64Images());
        userPostModel.setUserID(objectId);
        userPostModel.setBase64Images(base64Images);
        userPostModel.setName(userPost.getName());
        userPostModel.setPostDescription(userPost.getPostDescription());
        userPostModel.setPostCategory(userPost.getPostCategory());
        userPostModel.setPostBrand(userPost.getPostBrand());
        userPostModel.setPostStyle(userPost.getPostStyle());
        userPostModel.setPostSize(userPost.getPostSize());
    }

    public void convertDTO(List<UserPostModel> userPostModels, UserAccountInfoModel userAccountInfoModel,  UserProfile userProfile) {

        userProfile.setName(userAccountInfoModel.getFullName());
        userProfile.setFollowersCount(userAccountInfoModel.getFollowers().size());
        userProfile.setFollowingCount(userAccountInfoModel.getFollowing().size());

        ArrayList<UserPost> userPosts = new ArrayList<>();

        for (UserPostModel userPostModel: userPostModels) {
            UserPost userPost = new UserPost();

            userPost.setBase64Images(userPostModel.getBase64Images());
            userPost.setName(userPostModel.getName());
            userPost.setPostDescription(userPostModel.getPostDescription());
            userPost.setPostCategory(userPostModel.getPostCategory());
            userPost.setPostBrand(userPostModel.getPostBrand());
            userPost.setPostStyle(userPostModel.getPostStyle());
            userPost.setPostSize(userPostModel.getPostSize());

            userPosts.add(userPost);

        }

        userProfile.setUserPosts(userPosts);

    }

}
