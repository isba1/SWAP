package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserProfileCompact;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    // get the follower or followee list based on the boolean parameter isGetFollowers
    // if the isGetFollowers is true, then it returns follower list of current user
    // otherwise, it returns the following list
    public List<UserProfileCompact> getFollowersOrFollowings(String loginUsername, boolean isGetFollowers) {
        List<UserProfileCompact> ret = new LinkedList<>();
        UserAccountInfoModel loginUser = userInfoRepository.findByEmail(loginUsername);
        Set<UUID> userList = loginUser.getFollowers();
        if (!isGetFollowers) {
            userList = loginUser.getFollowing();
        }
        for (UUID userId : userList) {
            UserAccountInfoModel user = userInfoRepository.findByUserID(userId);
            UserProfileCompact userProfileCompact = new UserProfileCompact();
            userProfileCompact.setUserID(userId);
            userProfileCompact.setUserName(user.getUserName());
            userProfileCompact.setFollowersCount(user.getFollowers().size());
            userProfileCompact.setFollowingCount(user.getFollowing().size());
            ret.add(userProfileCompact);
        }
        return ret;
    }
}
