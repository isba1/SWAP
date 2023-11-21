package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    DTOConverter dtoConverter;

    public void addFollower(String loginUserName, String userNameToFollow) {
        // find the user info model of two user
        UserAccountInfoModel loginUser = userInfoRepository.findByEmail(loginUserName);
        UserAccountInfoModel userToFollow = userInfoRepository.findByEmail(userNameToFollow);
        // add followers
        loginUser.getFollowing().add(userToFollow.getUserID());
        userToFollow.getFollowers().add(loginUser.getUserID());
        // save them
        userInfoRepository.save(loginUser);
        userInfoRepository.save(userToFollow);
    }

    public void removeFollowing(String loginUsername, String userNameToCancelFollowing) {
        // find the user info model of two user
        UserAccountInfoModel loginUser = userInfoRepository.findByEmail(loginUsername);
        UserAccountInfoModel userToCancelFollowing = userInfoRepository.findByEmail(userNameToCancelFollowing);
        // add followers
        loginUser.getFollowing().remove(userToCancelFollowing.getUserID());
        userToCancelFollowing.getFollowers().remove(loginUser.getUserID());
        // save them
        userInfoRepository.save(loginUser);
        userInfoRepository.save(userToCancelFollowing);
    }
}
