package com.SwapToSustain.Server.Repository;

import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserInfoRepository extends MongoRepository<UserAccountInfoModel, UUID> {

     UserAccountInfoModel findByEmailAndPassword(String email, String password);

     UserAccountInfoModel findByUserID(UUID userID);

     UserAccountInfoModel findByEmail(String email);

     UserAccountInfoModel findByUserName(String userName);


}
