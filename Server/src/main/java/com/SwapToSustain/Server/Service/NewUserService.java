package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.apache.catalina.User;
import org.apache.catalina.manager.DummyProxySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewUserService {

    @Autowired
    UserInfoRepository userInfoRepository;

    private void userAccountInfoToUserAccountInfoModel(UserAccountInfoModel userAccountInfoModel, UserAccountInfo userAccountInfo) {

        userAccountInfoModel.setUsername(userAccountInfo.getUsername());
        userAccountInfoModel.setPassword(userAccountInfo.getPassword());
        userAccountInfoModel.setFirstName(userAccountInfo.getFirstName());
        userAccountInfoModel.setLastName(userAccountInfo.getLastName());
        userAccountInfoModel.setEmail(userAccountInfo.getEmail());
        userAccountInfoModel.setPhone(userAccountInfo.getPhone());
        userAccountInfoModel.setAddress(userAccountInfo.getAddress());

    }


    public void saveAccountInfo(UserAccountInfo userAccountInfo){
        UserAccountInfoModel userAccountInfoModel = new UserAccountInfoModel();

        userAccountInfoToUserAccountInfoModel(userAccountInfoModel, userAccountInfo);

        userInfoRepository.save(userAccountInfoModel);
    }

    private void userInterestsToUserAccountInfoModel(UserAccountInfoModel userAccountInfoModel, UserInterests userInterests) {

        userAccountInfoModel.setInterestBrand(userInterests.getBrands());
        userAccountInfoModel.setInterestCategory(userInterests.getClothingCategory());
        userAccountInfoModel.setPantSize(userInterests.getPantSize());
        userAccountInfoModel.setShirtSize(userInterests.getShirtSize());
        userAccountInfoModel.setJacketSize(userInterests.getJacketSize());
        userAccountInfoModel.setShoeSize(userInterests.getShoeSize());

    }

    public void saveUserInterests(String username, String password, UserInterests userInterests){

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUsernameAndPassword(username, password);

        userInterestsToUserAccountInfoModel(userAccountInfoModel, userInterests);

        userInfoRepository.save(userAccountInfoModel);

    }

}
