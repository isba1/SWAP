package com.SwapToSustain.Server.Converter;

import com.SwapToSustain.Server.DTO.*;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.List;

@Component
public class DTOConverter {

    public void convertDTO(UserAccountInfoModel userAccountInfoModel, UserInterests userInterests){
        userAccountInfoModel.setInterestBrand(userInterests.getBrands());
        userAccountInfoModel.setInterestStyle(userInterests.getClothingStyle());
        userAccountInfoModel.setPantSize(userInterests.getPantSize());
        userAccountInfoModel.setShirtSize(userInterests.getShirtSize());
        userAccountInfoModel.setJacketSize(userInterests.getJacketSize());
        userAccountInfoModel.setShoeSize(userInterests.getShoeSize());
    }

    public void convertDTO(UserAccountInfoModel userAccountInfoModel, UserAccountInfo userAccountInfo) {
        userAccountInfoModel.setUserName(userAccountInfo.getUserName());
        userAccountInfoModel.setPassword(userAccountInfo.getPassword());
        userAccountInfoModel.setEmail(userAccountInfo.getEmail());
        userAccountInfoModel.setPhone(userAccountInfo.getPhone());
        userAccountInfoModel.setCity(userAccountInfo.getCity());
        userAccountInfoModel.setState(userAccountInfo.getState());
        userAccountInfoModel.setZipCode(userAccountInfo.getZipCode());
        userAccountInfoModel.setFollowers(new HashSet<>());
        userAccountInfoModel.setFollowing(new HashSet<>());
        userAccountInfoModel.setOfferedMe(new HashMap<>());
        userAccountInfoModel.setMyOffers(new HashMap<>());
    }


    public void convertDTO(UserPostModel userPostModel, NewUserPost newUserPost, UUID userId, String userName) {

        userPostModel.setUserID(userId);
        userPostModel.setUserName(userName);
        userPostModel.setPostName(newUserPost.getName());
        userPostModel.setPostDescription(newUserPost.getPostDescription());
        userPostModel.setPostCategory(newUserPost.getPostCategory());
        userPostModel.setPostBrand(newUserPost.getPostBrand());
        userPostModel.setPostStyle(newUserPost.getPostStyle());
        userPostModel.setPostSize(newUserPost.getPostSize());
        userPostModel.setConnectedUsers(new HashSet<>());
    }

    private void postModelToPersonalPost(UserPostModel userPostModel, UserPost userPost) {
        userPost.setGcsUrls(userPostModel.getGcsUrls());
        userPost.setPostID(userPostModel.getPostID());
        userPost.setName(userPostModel.getPostName());
        userPost.setPostDescription(userPostModel.getPostDescription());
        userPost.setPostCategory(userPostModel.getPostCategory());
        userPost.setPostBrand(userPostModel.getPostBrand());
        userPost.setPostStyle(userPostModel.getPostStyle());
        userPost.setPostSize(userPostModel.getPostSize());
    }

    public void convertDTO(List<UserPostModel> userPostModels, UserAccountInfoModel userAccountInfoModel,  UserProfile userProfile) {

        userProfile.setUserID(userAccountInfoModel.getUserID());
        userProfile.setUserName(userAccountInfoModel.getUserName());
        userProfile.setContactNumber(userAccountInfoModel.getPhone());
        userProfile.setFollowersCount(userAccountInfoModel.getFollowers().size());
        userProfile.setFollowingCount(userAccountInfoModel.getFollowing().size());
        userProfile.setUserCity(userAccountInfoModel.getCity());
        userProfile.setUserState(userAccountInfoModel.getState());

        ArrayList<UserPost> userPosts = new ArrayList<>();

        for (UserPostModel userPostModel: userPostModels) {

            UserPost userPost = new UserPost();

            postModelToPersonalPost(userPostModel, userPost);

            userPosts.add(userPost);

        }

        userProfile.setUserPosts(userPosts);

    }



    public TradesOffered convertDTO(UserPostModel myPost, UserPostModel theirPost) {

            TradesOffered newTradeOffer = new TradesOffered();
            newTradeOffer.setMyGcsUrls(myPost.getGcsUrls());
            newTradeOffer.setMyPostID(myPost.getPostID());
            newTradeOffer.setMyName(myPost.getPostName());
            newTradeOffer.setMyPostDescription(myPost.getPostDescription());
            newTradeOffer.setMyPostCategory(myPost.getPostCategory());
            newTradeOffer.setMyPostBrand(myPost.getPostBrand());
            newTradeOffer.setMyPostStyle(myPost.getPostStyle());
            newTradeOffer.setMyPostSize(myPost.getPostSize());
            newTradeOffer.setTheirGcsUrls(theirPost.getGcsUrls());
            newTradeOffer.setTheirPostID(theirPost.getPostID());
            newTradeOffer.setTheirUserID(theirPost.getUserID());
            newTradeOffer.setTheirUserName(theirPost.getUserName());
            newTradeOffer.setTheirPostName(theirPost.getPostName());
            newTradeOffer.setTheirPostDescription(theirPost.getPostDescription());
            newTradeOffer.setTheirPostCategory(theirPost.getPostCategory());
            newTradeOffer.setTheirPostBrand(theirPost.getPostBrand());
            newTradeOffer.setTheirPostStyle(theirPost.getPostStyle());
            newTradeOffer.setTheirPostSize(theirPost.getPostSize());

            return newTradeOffer;
    }

    public void convertDTOForPersonalPosts(List<UserPostModel> userPostModels, List<UserPost> userPosts) {

        for (UserPostModel userPostModel: userPostModels) {
            UserPost userPost = new UserPost();

            postModelToPersonalPost(userPostModel, userPost);

            userPosts.add(userPost);
        }

    }

    public void convertDTOForCompactProfile(List<UserProfileCompact> userProfileCompacts, List<UserAccountInfoModel> userAccountInfoModelList) {

        for (UserAccountInfoModel userAccountInfoModel : userAccountInfoModelList) {

            UserProfileCompact userProfileCompact = new UserProfileCompact();
            userProfileCompact.setUserID(userAccountInfoModel.getUserID());
            userProfileCompact.setUserName(userAccountInfoModel.getUserName());
            userProfileCompact.setFollowersCount(userAccountInfoModel.getFollowers().size());
            userProfileCompact.setFollowingCount(userAccountInfoModel.getFollowing().size());

            userProfileCompacts.add(userProfileCompact);
        }

    }


    public void convertDTOForFeedPosts (List<FeedUserPost> userFeedPosts, List<UserPostModel> userPostModels) {

        for (UserPostModel userPostModel: userPostModels) {

            FeedUserPost feedUserPost = new FeedUserPost();
            feedUserPost.setGcsUrls(userPostModel.getGcsUrls());
            feedUserPost.setPostID(userPostModel.getPostID());
            feedUserPost.setUserID(userPostModel.getUserID());
            feedUserPost.setUserName(userPostModel.getUserName());
            feedUserPost.setPostName(userPostModel.getPostName());
            feedUserPost.setPostDescription(userPostModel.getPostDescription());
            feedUserPost.setPostCategory(userPostModel.getPostCategory());
            feedUserPost.setPostBrand(userPostModel.getPostBrand());
            feedUserPost.setPostStyle(userPostModel.getPostStyle());
            feedUserPost.setPostSize(userPostModel.getPostSize());

            userFeedPosts.add(feedUserPost);
        }

    }

}
