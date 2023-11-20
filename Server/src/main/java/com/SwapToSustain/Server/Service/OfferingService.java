package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserPost;
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

    public void makeOffer(String buyerPostID, String buyerUserName, String sellerPostID, String sellerUserName) {

        UserAccountInfoModel sellerUserAccountInfoModel = userInfoRepository.findByUserName(sellerUserName);
        UserAccountInfoModel buyerUserAccountInfoModel = userInfoRepository.findByUserName(buyerUserName);

        sellerUserAccountInfoModel.getOfferedMe().put(UUID.fromString(sellerPostID), UUID.fromString(buyerPostID));
        buyerUserAccountInfoModel.getMyOffers().put(UUID.fromString(sellerPostID), UUID.fromString(buyerPostID));

        userInfoRepository.save(sellerUserAccountInfoModel);
        userInfoRepository.save(buyerUserAccountInfoModel);

    }

    public List<UserPost> getItemsToOffer(String userName) {

        ArrayList<UserPost> userPosts = new ArrayList<>();

        List<UserPostModel> userPostModels = userPostRepository.findAllByUserName(userName);

        dtoConverter.convertDTOForPersonalPosts(userPostModels, userPosts);

        return userPosts;

    }

    public void acceptOffer(String sellerPostID, String sellerUserName, String buyerPostID, String buyerUserName) {

        UserAccountInfoModel sellerAccountInfoModel = userInfoRepository.findByUserName(sellerUserName);
        sellerAccountInfoModel.getOfferedMe().remove(UUID.fromString(sellerPostID));

        userPostRepository.deleteByPostID(UUID.fromString(sellerPostID));

        UserAccountInfoModel buyerAccountInfoModel = userInfoRepository.findByUserName(buyerUserName);
        buyerAccountInfoModel.getMyOffers().remove(UUID.fromString(sellerPostID));

        userPostRepository.deleteByPostID(UUID.fromString(buyerPostID));

    }

    public void declineOffer(String sellerPostID, String sellerUserName, String buyerUserName) {

        UserAccountInfoModel sellerAccountInfoModel = userInfoRepository.findByUserName(sellerUserName);
        sellerAccountInfoModel.getOfferedMe().remove(UUID.fromString(sellerPostID));

        UserAccountInfoModel buyerAccountInfoModel = userInfoRepository.findByUserName(buyerUserName);
        buyerAccountInfoModel.getMyOffers().remove(UUID.fromString(sellerPostID));
    }


}
