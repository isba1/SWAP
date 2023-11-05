package com.SwapToSustain.Server.Converter;

import com.SwapToSustain.Server.DTO.*;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.List;

@Component
public class DTOConverter {

    @Autowired
    UserPostRepository userPostRepository;

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


    public void convertDTO(UserPostModel userPostModel, NewUserPost newUserPost, UUID userId) {

        ArrayList<String> base64Images = new ArrayList<>(newUserPost.getBase64Images());
        userPostModel.setUserID(userId);
        userPostModel.setBase64Images(base64Images);
        userPostModel.setName(newUserPost.getName());
        userPostModel.setPostDescription(newUserPost.getPostDescription());
        userPostModel.setPostCategory(newUserPost.getPostCategory());
        userPostModel.setPostBrand(newUserPost.getPostBrand());
        userPostModel.setPostStyle(newUserPost.getPostStyle());
        userPostModel.setPostSize(newUserPost.getPostSize());
    }

    private void postModelToPersonalPost(UserPostModel userPostModel, PersonalUserPost personalUserPost) {
        personalUserPost.setBase64Images(userPostModel.getBase64Images());
        personalUserPost.setPostID(userPostModel.getPostID());
        personalUserPost.setName(userPostModel.getName());
        personalUserPost.setPostDescription(userPostModel.getPostDescription());
        personalUserPost.setPostCategory(userPostModel.getPostCategory());
        personalUserPost.setPostBrand(userPostModel.getPostBrand());
        personalUserPost.setPostStyle(userPostModel.getPostStyle());
        personalUserPost.setPostSize(userPostModel.getPostSize());
    }

    public void convertDTO(List<UserPostModel> userPostModels, UserAccountInfoModel userAccountInfoModel,  UserProfile userProfile) {

        userProfile.setUserName(userAccountInfoModel.getUserName());
        userProfile.setFollowersCount(userAccountInfoModel.getFollowers().size());
        userProfile.setFollowingCount(userAccountInfoModel.getFollowing().size());

        ArrayList<PersonalUserPost> personalUserPosts = new ArrayList<>();

        for (UserPostModel userPostModel: userPostModels) {

            PersonalUserPost personalUserPost = new PersonalUserPost();

            postModelToPersonalPost(userPostModel, personalUserPost);

            personalUserPosts.add(personalUserPost);

        }

        userProfile.setPersonalUserPosts(personalUserPosts);

    }



    public TradesOffered convertDTO(UserPostModel myPost, UserPostModel theirPost) {

            TradesOffered newTradeOffer = new TradesOffered();
            newTradeOffer.setMyBase64Images(myPost.getBase64Images());
            newTradeOffer.setMyPostID(myPost.getPostID());
            newTradeOffer.setMyName(myPost.getName());
            newTradeOffer.setMyPostDescription(myPost.getPostDescription());
            newTradeOffer.setMyPostCategory(myPost.getPostCategory());
            newTradeOffer.setMyPostBrand(myPost.getPostBrand());
            newTradeOffer.setMyPostStyle(myPost.getPostStyle());
            newTradeOffer.setMyPostSize(myPost.getPostSize());
            newTradeOffer.setTheirBase64Images(theirPost.getBase64Images());
            newTradeOffer.setTheirPostID(theirPost.getPostID());
            newTradeOffer.setTheirUserID(theirPost.getUserID());
            newTradeOffer.setTheirUserName(theirPost.getName());
            newTradeOffer.setTheirPostDescription(theirPost.getPostDescription());
            newTradeOffer.setTheirPostCategory(theirPost.getPostCategory());
            newTradeOffer.setTheirPostBrand(theirPost.getPostBrand());
            newTradeOffer.setTheirPostStyle(theirPost.getPostStyle());
            newTradeOffer.setTheirPostSize(theirPost.getPostSize());

            return newTradeOffer;
    }

    public void convertDTO (List<UserPostModel> userPostModels, ArrayList<PersonalUserPost> personalUserPosts) {

        for (UserPostModel userPostModel: userPostModels) {
            PersonalUserPost personalUserPost = new PersonalUserPost();

            postModelToPersonalPost(userPostModel, personalUserPost);

            personalUserPosts.add(personalUserPost);
        }

    }

    public void convertDTO (List<UserProfileSearch> userProfileSearches, List<UserAccountInfoModel> userAccountInfoModelList) {

        for (UserAccountInfoModel userAccountInfoModel : userAccountInfoModelList) {

            UserProfileSearch userProfileSearch = new UserProfileSearch();
            userProfileSearch.setUserID(userAccountInfoModel.getUserID());
            userProfileSearch.setUserName(userAccountInfoModel.getUserName());
            userProfileSearch.setFollowersCount(userAccountInfoModel.getFollowers().size());
            userProfileSearch.setFollowingCount(userAccountInfoModel.getFollowing().size());

            userProfileSearches.add(userProfileSearch);
        }

    }

}
