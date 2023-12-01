package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Components.RemovingPosts;
import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import com.google.auth.Credentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class OfferingService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private RemovingPosts removePosts;


    public void makeOffer(String buyerPostID, String buyerUserName, String sellerPostID, String sellerUserName) {

        UserAccountInfoModel sellerUserAccountInfoModel = userInfoRepository.findByUserName(sellerUserName);
        UserAccountInfoModel buyerUserAccountInfoModel = userInfoRepository.findByUserName(buyerUserName);

        if (sellerUserAccountInfoModel.getOfferedMe().containsKey(UUID.fromString(sellerPostID))) {
            sellerUserAccountInfoModel.getOfferedMe().get(UUID.fromString(sellerPostID)).add(UUID.fromString(buyerPostID));
        } else {
            ArrayList<UUID> newList = new ArrayList<>();
            newList.add(UUID.fromString(buyerPostID));
            sellerUserAccountInfoModel.getOfferedMe().put(UUID.fromString(sellerPostID), newList);
        }

        buyerUserAccountInfoModel.getMyOffers().put(UUID.fromString(sellerPostID), UUID.fromString(buyerPostID));

        UserPostModel sellerUserPost = userPostRepository.findByPostID(UUID.fromString(sellerPostID));
        UserPostModel buyerUserPost = userPostRepository.findByPostID(UUID.fromString(buyerPostID));

        sellerUserPost.getConnectedUsers().add(buyerUserName);
        buyerUserPost.getConnectedUsers().add(sellerUserName);

        userInfoRepository.save(sellerUserAccountInfoModel);
        userInfoRepository.save(buyerUserAccountInfoModel);

        userPostRepository.save(sellerUserPost);
        userPostRepository.save(buyerUserPost);

    }

    public List<UserPost> getItemsToOffer(String userName) {

        ArrayList<UserPost> userPosts = new ArrayList<>();

        List<UserPostModel> userPostModels = userPostRepository.findAllByUserName(userName);

        dtoConverter.convertDTOForPersonalPosts(userPostModels, userPosts);

        return userPosts;

    }



    public void acceptOffer(String sellerPostID, String sellerUserName, String buyerPostID, String buyerUserName) {
        // seller post handling
        UserPostModel sellerPost = userPostRepository.findByPostID(UUID.fromString(sellerPostID));
        removePosts.deleteFromGCS(sellerPost);

        removePosts.removeOffersFromAllUsers(sellerPost, sellerUserName);


        // buyer post handling
        UserPostModel buyerPost = userPostRepository.findByPostID(UUID.fromString(buyerPostID));
        removePosts.deleteFromGCS(buyerPost);

        removePosts.removeOffersFromAllUsers(buyerPost, buyerUserName);

        userPostRepository.deleteByPostID(UUID.fromString(sellerPostID));
        userPostRepository.deleteByPostID(UUID.fromString(buyerPostID));

    }

    public void declineOffer(String sellerPostID, String sellerUserName, String buyerUserName) {

        UserAccountInfoModel sellerAccountInfoModel = userInfoRepository.findByUserName(sellerUserName);
        sellerAccountInfoModel.getOfferedMe().remove(UUID.fromString(sellerPostID));

        userInfoRepository.save(sellerAccountInfoModel);

        UserAccountInfoModel buyerAccountInfoModel = userInfoRepository.findByUserName(buyerUserName);
        buyerAccountInfoModel.getMyOffers().remove(UUID.fromString(sellerPostID));

        userInfoRepository.save(buyerAccountInfoModel);
    }


}
