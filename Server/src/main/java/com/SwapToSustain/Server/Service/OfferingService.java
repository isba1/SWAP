package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.PersonalUserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferingService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    DTOConverter dtoConverter;

    public void makeOffer(String buyerPostID, String buyerUserID, String sellerPostID, String sellerUserID) {

        UserAccountInfoModel sellerUserAccountInfoModel = userInfoRepository.findByUserID(new ObjectId(sellerUserID));
        UserAccountInfoModel buyerUserAccountInfoModel = userInfoRepository.findByUserID(new ObjectId(buyerUserID));

        sellerUserAccountInfoModel.getOfferedMe().put(new ObjectId(sellerPostID), new ObjectId(buyerPostID));
        buyerUserAccountInfoModel.getMyOffers().put(new ObjectId(sellerPostID), new ObjectId(buyerPostID));

        userInfoRepository.save(sellerUserAccountInfoModel);
        userInfoRepository.save(buyerUserAccountInfoModel);

    }

    public List<PersonalUserPost> getItemsToOffer(String userID) {

        ArrayList<PersonalUserPost> personalUserPosts = new ArrayList<>();

        List<UserPostModel> userPostModels = userPostRepository.findAllByUserID(new ObjectId(userID));

        dtoConverter.convertDTO(userPostModels, personalUserPosts);

        return personalUserPosts;

    }


}
