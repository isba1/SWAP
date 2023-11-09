package com.SwapToSustain.Server.Repository;

import com.SwapToSustain.Server.DTO.UserSearchCriteria;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepository {

    private final MongoTemplate mongoTemplate;

    public CustomRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public List<UserAccountInfoModel> findByDynamicCriteria(UserSearchCriteria userSearchCriteria) {
        Query query = new Query();

        if (!userSearchCriteria.getPantSize().contains("null")) {
            query.addCriteria(Criteria.where("pantSize").is(userSearchCriteria.getPantSize().trim()));
        }

        if (!userSearchCriteria.getShirtSize().contains("null")) {
            query.addCriteria(Criteria.where("shirtSize").is(userSearchCriteria.getShirtSize().trim()));
        }

        if (!userSearchCriteria.getJacketSize().contains("null")) {
            query.addCriteria(Criteria.where("jacketSize").is(userSearchCriteria.getJacketSize().trim()));
        }

        if (!userSearchCriteria.getInterestStyle().contains("null")) {
            query.addCriteria(Criteria.where("interestStyle").in(userSearchCriteria.getInterestStyle().trim()));
        }

        if (!userSearchCriteria.getInterestBrand().contains("null")) {
            query.addCriteria(Criteria.where("interestBrand").in(userSearchCriteria.getInterestBrand().trim()));
        }

        return mongoTemplate.find(query, UserAccountInfoModel.class);
    }

}
