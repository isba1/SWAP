package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private UserPostRepository userPostRepository;

    public boolean userAuthentication(String email, String password){

        // use userInfoRepository method findByUsernameAndPassword
        final UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByEmailAndPassword(email, password);
        // if it returns null, then the account doesn't exist, return false
        // if it returns an account, then the account exists, return true
        return userAccountInfoModel != null;
    }

    public boolean saveAccountInfo(UserAccountInfo userAccountInfo){
        if (userInfoRepository.findByEmail(userAccountInfo.getEmail()) != null || userInfoRepository.findByUserName(userAccountInfo.getUserName()) != null) {
            return false;
        } else {
            UserAccountInfoModel userAccountInfoModel = new UserAccountInfoModel();

            dtoConverter.convertDTO(userAccountInfoModel, userAccountInfo);

            userInfoRepository.save(userAccountInfoModel);

            return true;
        }


    }

    public void deleteUser(String email, String password){
        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByEmailAndPassword(email, password);

        List<UserPostModel> allPosts = userPostRepository.findAllByUserID(userAccountInfoModel.getUserID());

        userPostRepository.deleteAll(allPosts);

        userInfoRepository.delete(userAccountInfoModel);

    }

}
