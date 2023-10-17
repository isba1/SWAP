package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewUserService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    private DTOConverter dtoConverter;

    public void saveAccountInfo(UserAccountInfo userAccountInfo){
        UserAccountInfoModel userAccountInfoModel = new UserAccountInfoModel();

        dtoConverter.convertDTO(userAccountInfoModel, userAccountInfo);

        userInfoRepository.save(userAccountInfoModel);
    }


    public void saveUserInterests(String username, String password, UserInterests userInterests){

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUsernameAndPassword(username, password);

        dtoConverter.convertDTO(userAccountInfoModel, userInterests);

        userInfoRepository.save(userAccountInfoModel);

    }

}
