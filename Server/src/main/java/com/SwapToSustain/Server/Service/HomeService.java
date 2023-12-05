package com.SwapToSustain.Server.Service;


import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.FeedUserPost;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeService {

    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    DTOConverter dtoConverter;

    public List<FeedUserPost> getRecommendedFeed(String userName) {

        List<FeedUserPost> recommendedFeedPosts = new ArrayList<>();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserName(userName);

        List<UserPostModel> recommendedByStyleAndBrand = userPostRepository.findByPostStyleOrPostBrandOrPostSizeAndNotUserIdIn
                (userAccountInfoModel.getInterestStyle(), userAccountInfoModel.getInterestBrand(), userAccountInfoModel.getUserID());

        List<UserPostModel> recommendedByShirtSize = userPostRepository.findByShirtSizeAndNotUserId(userAccountInfoModel.getShirtSize(), userAccountInfoModel.getUserID());
        List<UserPostModel> recommendedByPantSize = userPostRepository.findByPantSizeAndNotUserId(userAccountInfoModel.getPantSize(), userAccountInfoModel.getUserID());
        List<UserPostModel> recommendedByJacketSize = userPostRepository.findByJacketSizeAndNotUserId(userAccountInfoModel.getJacketSize(), userAccountInfoModel.getUserID());
        List<UserPostModel> recommendedByShoeSize = userPostRepository.findByShoeSizeAndNotUserId(userAccountInfoModel.getShoeSize(), userAccountInfoModel.getUserID());

        List<UserPostModel> allRecommended = new ArrayList<>();

        for (UUID userID: userAccountInfoModel.getFollowing()) {
            List<UserPostModel> singleFollowingFeed = userPostRepository.findAllByUserID(userID);
            allRecommended.addAll(singleFollowingFeed);
        }

        allRecommended.addAll(recommendedByStyleAndBrand);
        allRecommended.addAll(recommendedByShirtSize);
        allRecommended.addAll(recommendedByPantSize);
        allRecommended.addAll(recommendedByJacketSize);
        allRecommended.addAll(recommendedByShoeSize);


        HashSet<UUID> foundPostIds = new HashSet<>();
        ArrayList<UserPostModel> noDuplicatesUserPostModels = new ArrayList<>();

        for (UserPostModel userPostModel: allRecommended) {
            if (!foundPostIds.contains(userPostModel.getPostID())) {
                noDuplicatesUserPostModels.add(userPostModel);
                foundPostIds.add(userPostModel.getPostID());
            }
        }


        dtoConverter.convertDTOForFeedPosts(recommendedFeedPosts, noDuplicatesUserPostModels);


        return recommendedFeedPosts;

    }

    public List<FeedUserPost> exploreCategories(String userID, String category) {

        List<FeedUserPost> feedPosts = new ArrayList<>();
        List<UserPostModel> userPostModels = userPostRepository.findByPostCategoryAAndNAndUserName(category, userID);

        dtoConverter.convertDTOForFeedPosts(feedPosts, userPostModels);

        return feedPosts;
    }


}
