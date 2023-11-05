package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserProfileSearch;
import com.SwapToSustain.Server.DTO.UserSearchCriteria;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    public List<UserProfileSearch> findUsers(UserSearchCriteria userSearchCriteria) throws IllegalAccessException {

        List<Document> criteria = new ArrayList<>();

        Class<?> clazz = userSearchCriteria.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.get(userSearchCriteria) != null) {
                criteria.add(new Document(field.getName(), field.get(userSearchCriteria)));
            }
        }


        List<UserAccountInfoModel> userAccountInfoModelList = userInfoRepository.findByCriteria(criteria);

        List<UserProfileSearch> userProfileSearches = new ArrayList<>();

        dtoConverter.convertDTO(userProfileSearches, userAccountInfoModelList);

        return userProfileSearches;
    }

}
