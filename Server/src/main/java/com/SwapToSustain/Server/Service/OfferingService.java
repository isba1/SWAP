package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Components.RemovingPosts;
import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserNotification;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
            if (!sellerUserAccountInfoModel.getOfferedMe().get(UUID.fromString(sellerPostID)).contains(UUID.fromString(buyerPostID))) {
                boolean exists = false;

                for (UUID id: sellerUserAccountInfoModel.getOfferedMe().get(UUID.fromString(sellerPostID))) {
                    UserPostModel foundPost = userPostRepository.findByPostID(id);
                    if (Objects.equals(foundPost.getUserName(), buyerUserAccountInfoModel.getUserName())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    sellerUserAccountInfoModel.getOfferedMe().get(UUID.fromString(sellerPostID)).add(UUID.fromString(buyerPostID));
                }
            }
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
        UserPostModel buyerPost = userPostRepository.findByPostID(UUID.fromString(buyerPostID));

        UserAccountInfoModel buyerUserAccount = userInfoRepository.findByUserName(buyerUserName);
        addBuyerNotificationForCompletedOffer(buyerUserAccount, sellerUserName, sellerPost, true);

        removePosts.deleteFromGCS(sellerPost);

        removePosts.removeOffersFromAllUsers(sellerPost, sellerUserName, buyerUserAccount);

        // buyer post handling
        removePosts.deleteFromGCS(buyerPost);

        removePosts.removeOffersFromAllUsers(buyerPost, buyerUserName, buyerUserAccount);
//        removePosts.attempt2(buyerPost, buyerUserName, null);

        userPostRepository.deleteByPostID(UUID.fromString(sellerPostID));
        userPostRepository.deleteByPostID(UUID.fromString(buyerPostID));

    }

    private void addBuyerNotificationForCompletedOffer(UserAccountInfoModel buyerUserAccount, String sellerUserName, UserPostModel sellerPost, boolean isAccepted) {
        buyerUserAccount.getNotifications().add(new UserNotification(UUID.randomUUID(),"available", isAccepted, sellerUserName, sellerPost.getPostName()));
        userInfoRepository.save(buyerUserAccount);
    }

    public void declineOffer(String sellerPostID, String sellerUserName, String buyerUserName, String buyerPostId) {
        UserPostModel sellerPost = userPostRepository.findByPostID(UUID.fromString(sellerPostID));

        UserAccountInfoModel buyerAccountInfoModel = userInfoRepository.findByUserName(buyerUserName);
        addBuyerNotificationForCompletedOffer(buyerAccountInfoModel, sellerUserName, sellerPost, false);

        UserAccountInfoModel sellerAccountInfoModel = userInfoRepository.findByUserName(sellerUserName);

        ArrayList<UUID> offersForSellerPost = sellerAccountInfoModel.getOfferedMe().get(UUID.fromString(sellerPostID));
        offersForSellerPost.remove(UUID.fromString(buyerPostId));
        sellerAccountInfoModel.getOfferedMe().put(UUID.fromString(sellerPostID), offersForSellerPost);

        UserPostModel sellerPostModel = userPostRepository.findByPostID(UUID.fromString(sellerPostID));
        sellerPostModel.getConnectedUsers().remove(buyerUserName);

        userInfoRepository.save(sellerAccountInfoModel);
        userPostRepository.save(sellerPostModel);

        buyerAccountInfoModel.getMyOffers().remove(UUID.fromString(sellerPostID));

        UserPostModel buyerPostModel = userPostRepository.findByPostID(UUID.fromString(buyerPostId));
        buyerPostModel.getConnectedUsers().remove(sellerUserName);

        userPostRepository.save(buyerPostModel);
        userInfoRepository.save(buyerAccountInfoModel);
    }
}
