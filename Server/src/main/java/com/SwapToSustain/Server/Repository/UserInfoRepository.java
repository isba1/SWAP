package com.SwapToSustain.Server.Repository;

import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserInfoRepository extends MongoRepository<UserAccountInfoModel, UUID> {

    public UserAccountInfoModel findByEmailAndPassword(String email, String password);

}
