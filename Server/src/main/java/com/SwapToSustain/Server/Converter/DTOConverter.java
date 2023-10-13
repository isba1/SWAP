package com.SwapToSustain.Server.Converter;

import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import org.springframework.stereotype.Component;

@Component
public interface DTOConverter {

    void convertDTO(UserAccountInfoModel userAccountInfoModel, UserInterests userInterests);

    void convertDTO(UserAccountInfoModel userAccountInfoModel, UserAccountInfo userAccountInfo);

}
