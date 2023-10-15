package com.SwapToSustain.Server.Repository;

import com.SwapToSustain.Server.Model.UserPostModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserPostRepository extends MongoRepository<UserPostModel, UUID> {

}
