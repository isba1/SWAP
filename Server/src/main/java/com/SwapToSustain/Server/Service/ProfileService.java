package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.TradesOffered;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserID(new ObjectId(userID));
        List<UserPostModel> userPostModels = userPostRepository.findAllByUserID(new ObjectId(userID));

        dtoConverter.convertDTO(userPostModels, userAccountInfoModel, userProfile);

        return userProfile;
    }

    public List<TradesOffered> getTradesOfferedMe(String userID) {

        ArrayList<TradesOffered> tradeOffers = new ArrayList<>();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserID(new ObjectId(userID));
        HashMap<ObjectId, ObjectId> tradeOfferIDMap = userAccountInfoModel.getOfferedMe();

        for (Map.Entry<ObjectId, ObjectId> entry : tradeOfferIDMap.entrySet()) {
            UserPostModel myPost = userPostRepository.findByPostID(entry.getKey());
            UserPostModel theirPost = userPostRepository.findByPostID(entry.getValue());

            tradeOffers.add(dtoConverter.convertDTO(myPost, theirPost));

        }

        return tradeOffers;

    }

    public List<TradesOffered> getTradesIOffer(String userID) {
        ArrayList<TradesOffered> tradeOffers = new ArrayList<>();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserID(new ObjectId(userID));
        HashMap<ObjectId, ObjectId> tradeOfferIDMap = userAccountInfoModel.getMyOffers();

        for (Map.Entry<ObjectId, ObjectId> entry : tradeOfferIDMap.entrySet()) {
            UserPostModel myPost = userPostRepository.findByPostID(entry.getValue());
            UserPostModel theirPost = userPostRepository.findByPostID(entry.getKey());

            tradeOffers.add(dtoConverter.convertDTO(myPost, theirPost));

        }

        return tradeOffers;
    }

}
