package com.SwapToSustain.Server.Repository;

import com.SwapToSustain.Server.DTO.UserSearchCriteria;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
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

//    public CustomRepository(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }

    public List<UserAccountInfoModel> findByDynamicCriteria(UserSearchCriteria userSearchCriteria) {
        Query query = new Query();

        if (!userSearchCriteria.getPantSize().contains("null")) {
            System.out.print("added pant size");
            query.addCriteria(Criteria.where("pantSize").is(userSearchCriteria.getPantSize()));
        }

        if (!userSearchCriteria.getShirtSize().contains("null")) {
            System.out.print("added shirt size");

            query.addCriteria(Criteria.where("shirtSize").is(userSearchCriteria.getShirtSize()));
        }

        if (!userSearchCriteria.getJacketSize().contains("null")) {
            System.out.print("added jacket size");

            query.addCriteria(Criteria.where("jacketSize").is(userSearchCriteria.getJacketSize()));
        }

        if (!userSearchCriteria.getInterestStyle().contains("null")) {
            System.out.println(userSearchCriteria.getInterestStyle());

            query.addCriteria(Criteria.where("interestStyle").in(userSearchCriteria.getInterestStyle()));
        }

        if (!userSearchCriteria.getInterestBrand().contains("null")) {
            System.out.println(userSearchCriteria.getInterestBrand());

            query.addCriteria(Criteria.where("interestBrand").in(userSearchCriteria.getInterestBrand()));
        }

        return mongoTemplate.find(query, UserAccountInfoModel.class);
    }

}
