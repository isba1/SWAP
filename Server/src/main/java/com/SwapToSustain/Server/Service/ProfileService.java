package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.TradesOffered;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.DTO.UserProfileCompact;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfileService {

    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    DTOConverter dtoConverter;

    public UserProfile getUserProfileInfo(String userName) {
        UserProfile userProfile = new UserProfile();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserName(userName);
        List<UserPostModel> userPostModels = userPostRepository.findAllByUserName(userName);

        dtoConverter.convertDTO(userPostModels, userAccountInfoModel, userProfile);

        return userProfile;
    }

    public List<TradesOffered> getTradesOfferedMe(String userName) {

        ArrayList<TradesOffered> tradeOffers = new ArrayList<>();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserName(userName);

        for (Map.Entry<UUID, ArrayList<UUID>> entry : userAccountInfoModel.getOfferedMe().entrySet()) {
            ArrayList<UUID> arrayList = entry.getValue();

            UserPostModel myPost = userPostRepository.findByPostID(entry.getKey());

            for (UUID postID : arrayList) {
                UserPostModel theirPost = userPostRepository.findByPostID(postID);
                tradeOffers.add(dtoConverter.convertDTO(myPost, theirPost));
            }

        }


        return tradeOffers;

    }

    public List<TradesOffered> getTradesIOffer(String userName) {
        ArrayList<TradesOffered> tradeOffers = new ArrayList<>();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserName(userName);
        HashMap<UUID, UUID> tradeOfferIDMap = userAccountInfoModel.getMyOffers();

        for (Map.Entry<UUID, UUID> entry : tradeOfferIDMap.entrySet()) {
            UserPostModel myPost = userPostRepository.findByPostID(entry.getValue());
            UserPostModel theirPost = userPostRepository.findByPostID(entry.getKey());

            tradeOffers.add(dtoConverter.convertDTO(myPost, theirPost));

        }

        return tradeOffers;
    }

    public List<UserProfileCompact> getMyFollowers(String userName) {

        UserAccountInfoModel myAccount = userInfoRepository.findByUserName(userName);
        List<UserAccountInfoModel> followersAccountInfoModels = new ArrayList<>();

        for (UUID id: myAccount.getFollowers()) {
            UserAccountInfoModel followerAccountInfoModel = userInfoRepository.findByUserID(id);
            followersAccountInfoModels.add(followerAccountInfoModel);
        }

        List<UserProfileCompact> userProfileCompacts = new ArrayList<>();
        dtoConverter.convertDTOForCompactProfile(userProfileCompacts, followersAccountInfoModels);

        return userProfileCompacts;
    }

    public List<UserProfileCompact> getMyFollowing(String userName) {
        UserAccountInfoModel myAccount = userInfoRepository.findByUserName(userName);
        List<UserAccountInfoModel> followingAccountInfoModels = new ArrayList<>();

        for (UUID id: myAccount.getFollowing()) {
            UserAccountInfoModel followingAccountInfoModel = userInfoRepository.findByUserID(id);
            followingAccountInfoModels.add(followingAccountInfoModel);
        }

        List<UserProfileCompact> userProfileCompacts = new ArrayList<>();
        dtoConverter.convertDTOForCompactProfile(userProfileCompacts, followingAccountInfoModels);

        return userProfileCompacts;

    }

    public UserProfile getIndividualProfile(String userName) {

        UserAccountInfoModel userAccountInfoModelFound = userInfoRepository.findByUserName(userName);
        List<UserPostModel> userPostModels = userPostRepository.findAllByUserID(userAccountInfoModelFound.getUserID());

        UserProfile userProfile = new UserProfile();

        dtoConverter.convertDTO(userPostModels, userAccountInfoModelFound, userProfile);

        return userProfile;
    }

}
