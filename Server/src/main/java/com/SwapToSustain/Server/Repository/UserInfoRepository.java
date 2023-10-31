package com.SwapToSustain.Server.Repository;

import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserInfoRepository extends MongoRepository<UserAccountInfoModel, UUID> {

     UserAccountInfoModel findByEmailAndPassword(String email, String password);

     UserAccountInfoModel findByUserID(ObjectId objectID);

     UserAccountInfoModel findByEmail(String email);

     UserAccountInfoModel findByUserName(String userName);



}
