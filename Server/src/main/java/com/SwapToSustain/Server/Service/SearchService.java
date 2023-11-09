package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserProfileSearch;
import com.SwapToSustain.Server.DTO.UserSearchCriteria;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Repository.CustomRepository;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SearchService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private CustomRepository customRepository;

    public List<UserProfileSearch> findUsers(UserSearchCriteria userSearchCriteria) throws IllegalAccessException {
        List<UserProfileSearch> userProfileSearches = new ArrayList<>();

        if (!Objects.equals(userSearchCriteria.getUserName(), "null")) {

            UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByUserName(userSearchCriteria.getUserName());
            List<UserAccountInfoModel> userAccountInfoModelList = new ArrayList<>();
            userAccountInfoModelList.add(userAccountInfoModel);

            dtoConverter.convertDTO(userProfileSearches, userAccountInfoModelList);

        } else {

            List<UserAccountInfoModel> userAccountInfoModelList = customRepository.findByDynamicCriteria(userSearchCriteria);
            dtoConverter.convertDTO(userProfileSearches, userAccountInfoModelList);

        }

        return userProfileSearches;
    }
}
