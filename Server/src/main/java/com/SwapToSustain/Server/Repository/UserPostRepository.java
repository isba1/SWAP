package com.SwapToSustain.Server.Repository;

import com.SwapToSustain.Server.Model.UserPostModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface UserPostRepository extends MongoRepository<UserPostModel, UUID> {

    List<UserPostModel> findAllByUserID(ObjectId userID);

    UserPostModel findByPostID(ObjectId postID);

    void deleteByPostID(ObjectId postID);

}
