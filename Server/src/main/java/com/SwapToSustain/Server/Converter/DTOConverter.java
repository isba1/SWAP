package com.SwapToSustain.Server.Converter;

import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

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
    }


    public void convertDTO(UserPostModel userPostModel, UserPost userPost, ObjectId objectId) {
        String base64Image = Base64.getEncoder().encodeToString(userPost.getImageBinary());
        userPostModel.setUserID(objectId);
        userPostModel.setBase64Image(base64Image);
        userPostModel.setPostDescription(userPost.getPostDescription());
        userPostModel.setPostCategories(userPost.getPostCategories());
    }

}
