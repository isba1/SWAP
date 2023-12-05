package com.SwapToSustain.Server.Converter;

import com.SwapToSustain.Server.DTO.*;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.google.auth.Credentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DTOConverter {

    private final Credentials credentials;
    private final Storage storage;
    private final String bucketName;

    @Autowired
    public DTOConverter(Credentials credentials, Storage storage, String bucketName) {
        this.credentials = credentials;
        this.storage = storage;
        this.bucketName = bucketName;
    }

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

        switch (newUserPost.getPostCategory()) {
            case "Tops" -> userPostModel.setShirtSize(newUserPost.getPostSize());
            case "Bottoms" -> userPostModel.setPantSize(newUserPost.getPostSize());
            case "Outerwear" -> userPostModel.setJacketSize(newUserPost.getPostSize());
            case "Shoes" -> userPostModel.setShoeSize(newUserPost.getPostSize());
        }

        userPostModel.setConnectedUsers(new HashSet<>());
    }

    private List<BlobInfo> generateBlobInfo(String bucketName, List<String> gcsObjectNames) {
        ArrayList<BlobInfo> blobInfos = new ArrayList<>();
        for (String gcsObjectName: gcsObjectNames) {
            blobInfos.add(BlobInfo.newBuilder(BlobId.of(bucketName, gcsObjectName)).build());
        }
        return blobInfos;
    }

    private List<String> generateSignedUrl(List<BlobInfo> blobInfos) {
        ArrayList<String> signedUrls = new ArrayList<>();

        for (BlobInfo blobInfo: blobInfos) {
            URL signedUrl = storage.signUrl(blobInfo, 5, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
            signedUrls.add(signedUrl.toString());
        }
        return signedUrls;
    }

    private void postModelToPersonalPost(UserPostModel userPostModel, UserPost userPost) {
        List<BlobInfo> blobInfos = generateBlobInfo(bucketName, userPostModel.getGcsObjectNames());

        userPost.setGcsUrls(generateSignedUrl(blobInfos));
        userPost.setPostID(userPostModel.getPostID());
        userPost.setName(userPostModel.getPostName());
        userPost.setPostDescription(userPostModel.getPostDescription());
        userPost.setPostCategory(userPostModel.getPostCategory());
        userPost.setPostBrand(userPostModel.getPostBrand());
        userPost.setPostStyle(userPostModel.getPostStyle());

        switch (userPostModel.getPostCategory()) {
            case "Tops" -> userPost.setPostSize(userPostModel.getShirtSize());
            case "Bottoms" -> userPost.setPostSize(userPostModel.getPantSize());
            case "Outerwear" -> userPost.setPostSize(userPostModel.getJacketSize());
            case "Shoes" -> userPost.setPostSize(userPostModel.getShoeSize());
        }

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

        List<BlobInfo> myBlobInfos = generateBlobInfo(bucketName, myPost.getGcsObjectNames());
        List<BlobInfo> theirBlobInfos = generateBlobInfo(bucketName, theirPost.getGcsObjectNames());


        TradesOffered newTradeOffer = new TradesOffered();
            newTradeOffer.setMyGcsUrls(generateSignedUrl(myBlobInfos));
            newTradeOffer.setMyPostID(myPost.getPostID());
            newTradeOffer.setMyName(myPost.getPostName());
            newTradeOffer.setMyPostDescription(myPost.getPostDescription());
            newTradeOffer.setMyPostCategory(myPost.getPostCategory());
            newTradeOffer.setMyPostBrand(myPost.getPostBrand());
            newTradeOffer.setMyPostStyle(myPost.getPostStyle());

            switch (myPost.getPostCategory()) {
                case "Tops" -> newTradeOffer.setMyPostSize(myPost.getShirtSize());
                case "Bottoms" -> newTradeOffer.setMyPostSize(myPost.getPantSize());
                case "Outerwear" -> newTradeOffer.setMyPostSize(myPost.getJacketSize());
                case "Shoes" -> newTradeOffer.setMyPostSize(myPost.getShoeSize());
            }

            newTradeOffer.setTheirGcsUrls(generateSignedUrl(theirBlobInfos));
            newTradeOffer.setTheirPostID(theirPost.getPostID());
            newTradeOffer.setTheirUserID(theirPost.getUserID());
            newTradeOffer.setTheirUserName(theirPost.getUserName());
            newTradeOffer.setTheirPostName(theirPost.getPostName());
            newTradeOffer.setTheirPostDescription(theirPost.getPostDescription());
            newTradeOffer.setTheirPostCategory(theirPost.getPostCategory());
            newTradeOffer.setTheirPostBrand(theirPost.getPostBrand());
            newTradeOffer.setTheirPostStyle(theirPost.getPostStyle());

            switch (theirPost.getPostCategory()) {
                case "Tops" -> newTradeOffer.setTheirPostSize(theirPost.getShirtSize());
                case "Bottoms" -> newTradeOffer.setTheirPostSize(theirPost.getPantSize());
                case "Outerwear" -> newTradeOffer.setTheirPostSize(theirPost.getJacketSize());
                case "Shoes" -> newTradeOffer.setTheirPostSize(theirPost.getShoeSize());
            }

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

            List<BlobInfo> blobInfos = generateBlobInfo(bucketName, userPostModel.getGcsObjectNames());

            FeedUserPost feedUserPost = new FeedUserPost();
            feedUserPost.setGcsUrls(generateSignedUrl(blobInfos));
            feedUserPost.setPostID(userPostModel.getPostID());
            feedUserPost.setUserID(userPostModel.getUserID());
            feedUserPost.setUserName(userPostModel.getUserName());
            feedUserPost.setPostName(userPostModel.getPostName());
            feedUserPost.setPostDescription(userPostModel.getPostDescription());
            feedUserPost.setPostCategory(userPostModel.getPostCategory());
            feedUserPost.setPostBrand(userPostModel.getPostBrand());
            feedUserPost.setPostStyle(userPostModel.getPostStyle());

            switch (userPostModel.getPostCategory()) {
                case "Tops" -> feedUserPost.setPostSize(userPostModel.getShirtSize());
                case "Bottoms" -> feedUserPost.setPostSize(userPostModel.getPantSize());
                case "Outerwear" -> feedUserPost.setPostSize(userPostModel.getJacketSize());
                case "Shoes" -> feedUserPost.setPostSize(userPostModel.getShoeSize());
            }

            userFeedPosts.add(feedUserPost);
        }

    }

}
