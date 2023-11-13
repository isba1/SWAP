package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.PersonalUserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OfferingService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    DTOConverter dtoConverter;

    public void makeOffer(String buyerPostID, String buyerUserID, String sellerPostID, String sellerUserID) {

        UserAccountInfoModel sellerUserAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(sellerUserID));
        UserAccountInfoModel buyerUserAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(buyerUserID));

        sellerUserAccountInfoModel.getOfferedMe().put(UUID.fromString(sellerPostID), UUID.fromString(buyerPostID));
        buyerUserAccountInfoModel.getMyOffers().put(UUID.fromString(sellerPostID), UUID.fromString(buyerPostID));

        userInfoRepository.save(sellerUserAccountInfoModel);
        userInfoRepository.save(buyerUserAccountInfoModel);

    }

    public List<PersonalUserPost> getItemsToOffer(String userID) {

        ArrayList<PersonalUserPost> personalUserPosts = new ArrayList<>();

        List<UserPostModel> userPostModels = userPostRepository.findAllByUserID(UUID.fromString(userID));

        dtoConverter.convertDTOForPersonalPosts(userPostModels, personalUserPosts);

        return personalUserPosts;

    }

    public void acceptOffer(String sellerPostID, String sellerUserID, String buyerPostID, String buyerUserID) {

        UserAccountInfoModel sellerAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(sellerUserID));
        sellerAccountInfoModel.getOfferedMe().remove(UUID.fromString(sellerPostID));

        userPostRepository.deleteByPostID(UUID.fromString(sellerPostID));

        UserAccountInfoModel buyerAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(buyerUserID));
        buyerAccountInfoModel.getMyOffers().remove(UUID.fromString(sellerPostID));

        userPostRepository.deleteByPostID(UUID.fromString(buyerPostID));

    }

    public void declineOffer(String sellerPostID, String sellerUserID, String buyerUserID) {

        UserAccountInfoModel sellerAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(sellerUserID));
        sellerAccountInfoModel.getOfferedMe().remove(UUID.fromString(sellerPostID));

        UserAccountInfoModel buyerAccountInfoModel = userInfoRepository.findByUserID(UUID.fromString(buyerUserID));
        buyerAccountInfoModel.getMyOffers().remove(UUID.fromString(sellerPostID));
    }


}
