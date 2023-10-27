package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    UserPostRepository userPostRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    DTOConverter dtoConverter;

    public UserProfile getUserProfileInfo(String userID) {
        UserProfile userProfile = new UserProfile();

        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserID(new ObjectId(userID));
        List<UserPostModel> userPostModels = userPostRepository.findAllByUserID(new ObjectId(userID));

        dtoConverter.convertDTO(userPostModels, userAccountInfoModel, userProfile);

        return userProfile;
    }

}
