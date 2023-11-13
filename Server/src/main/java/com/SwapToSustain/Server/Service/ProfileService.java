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

    public UserProfile getUserProfileInfo(String userID) {
        UserProfile userProfile = new UserProfile();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(userID));
        List<UserPostModel> userPostModels = userPostRepository.findAllByUserID(UUID.fromString(userID));

        dtoConverter.convertDTO(userPostModels, userAccountInfoModel, userProfile);

        return userProfile;
    }

    public List<TradesOffered> getTradesOfferedMe(String userID) {

        ArrayList<TradesOffered> tradeOffers = new ArrayList<>();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(userID));
        HashMap<UUID, UUID> tradeOfferIDMap = userAccountInfoModel.getOfferedMe();

        for (Map.Entry<UUID, UUID> entry : tradeOfferIDMap.entrySet()) {
            UserPostModel myPost = userPostRepository.findByPostID(entry.getKey());
            UserPostModel theirPost = userPostRepository.findByPostID(entry.getValue());

            tradeOffers.add(dtoConverter.convertDTO(myPost, theirPost));

        }

        return tradeOffers;

    }

    public List<TradesOffered> getTradesIOffer(String userID) {
        ArrayList<TradesOffered> tradeOffers = new ArrayList<>();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(userID));
        HashMap<UUID, UUID> tradeOfferIDMap = userAccountInfoModel.getMyOffers();

        for (Map.Entry<UUID, UUID> entry : tradeOfferIDMap.entrySet()) {
            UserPostModel myPost = userPostRepository.findByPostID(entry.getValue());
            UserPostModel theirPost = userPostRepository.findByPostID(entry.getKey());

            tradeOffers.add(dtoConverter.convertDTO(myPost, theirPost));

        }

        return tradeOffers;
    }

    public List<UserProfileCompact> getMyFollowers(String userID) {

        UserAccountInfoModel myAccount = userInfoRepository.findByUserID(UUID.fromString(userID));
        List<UserAccountInfoModel> followersAccountInfoModels = new ArrayList<>();

        for (UUID id: myAccount.getFollowers()) {
            UserAccountInfoModel followerAccountInfoModel = userInfoRepository.findByUserID(id);
            followersAccountInfoModels.add(followerAccountInfoModel);
        }

        List<UserProfileCompact> userProfileCompacts = new ArrayList<>();
        dtoConverter.convertDTOForCompactProfile(userProfileCompacts, followersAccountInfoModels);

        return userProfileCompacts;
    }

    public List<UserProfileCompact> getMyFollowing(String userID) {
        UserAccountInfoModel myAccount = userInfoRepository.findByUserID(UUID.fromString(userID));
        List<UserAccountInfoModel> followingAccountInfoModels = new ArrayList<>();

        for (UUID id: myAccount.getFollowing()) {
            UserAccountInfoModel followingAccountInfoModel = userInfoRepository.findByUserID(id);
            followingAccountInfoModels.add(followingAccountInfoModel);
        }

        List<UserProfileCompact> userProfileCompacts = new ArrayList<>();
        dtoConverter.convertDTOForCompactProfile(userProfileCompacts, followingAccountInfoModels);

        return userProfileCompacts;

    }

    public UserProfile getIndividualProfile(String userID) {

        UserAccountInfoModel userAccountInfoModelFound = userInfoRepository.findByUserID(UUID.fromString(userID));
        List<UserPostModel> userPostModels = userPostRepository.findAllByUserID(UUID.fromString(userID));

        UserProfile userProfile = new UserProfile();

        dtoConverter.convertDTO(userPostModels, userAccountInfoModelFound, userProfile);

        return userProfile;
    }

}
