package com.SwapToSustain.Server.Converter;

import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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

    private static byte[] intArrayToByteArray(int[] intArray) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(intArray.length * 4); // 4 bytes for each int

        for (int value : intArray) {
            byteBuffer.putInt(value);
        }

        return byteBuffer.array();
    }

    public void convertDTO(UserPostModel userPostModel, UserPost userPost, ObjectId objectId) throws IOException {

        ArrayList<String> base64Images = new ArrayList<>(userPost.getBase64Images());
        userPostModel.setUserID(objectId);
        userPostModel.setBase64Images(base64Images);
        userPostModel.setName(userPost.getName());
        userPostModel.setPostDescription(userPost.getPostDescription());
        userPostModel.setPostCategory(userPost.getPostCategory());
        userPostModel.setPostBrand(userPost.getPostBrand());
    }

}
