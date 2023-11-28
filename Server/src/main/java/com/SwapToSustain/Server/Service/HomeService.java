package com.SwapToSustain.Server.Service;


import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.FeedUserPost;
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

        List<UserPostModel> recommendedByStyleAndBrand = userPostRepository.findByPostStyleOrPostBrandOrPostSizeIn
                (userAccountInfoModel.getInterestStyle(), userAccountInfoModel.getInterestBrand());

        List<UserPostModel> recommendedByShirtSize = userPostRepository.findByPostSize(userAccountInfoModel.getShirtSize());
        List<UserPostModel> recommendedByPantSize = userPostRepository.findByPostSize(userAccountInfoModel.getPantSize());
        List<UserPostModel> recommendedByJacketSize = userPostRepository.findByPostSize(userAccountInfoModel.getJacketSize());
        List<UserPostModel> recommendedByShoeSize = userPostRepository.findByPostSize(userAccountInfoModel.getShoeSize());

        List<UserPostModel> allRecommended = new ArrayList<>();
        allRecommended.addAll(recommendedByStyleAndBrand);
        allRecommended.addAll(recommendedByShirtSize);
        allRecommended.addAll(recommendedByPantSize);
        allRecommended.addAll(recommendedByJacketSize);
        allRecommended.addAll(recommendedByShoeSize);

        dtoConverter.convertDTOForFeedPosts(recommendedFeedPosts, allRecommended);

        return recommendedFeedPosts;

    }

}
