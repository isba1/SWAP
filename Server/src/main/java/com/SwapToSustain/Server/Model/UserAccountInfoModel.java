package com.SwapToSustain.Server.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Document(collection = "UserInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountInfoModel {

    @Id
    private UUID userID = UUID.randomUUID(); // unique id used by the DB
    private String userName;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String state;
    private String zipCode;
    private List<String> interestCategory;
    private List<String> interestBrand;
    private HashSet<UUID> following;
    private HashSet<UUID> followers;

    //first objectID is item yours (seller), and second one is the item that someone else has offered to trade (buyer)
    private HashMap<UUID,UUID> offeredMe;

    //first objectID is the item that I am interested in (seller), and the second one is the item that I am offering (buyer)
    private HashMap<UUID, UUID> myOffers;

    private String pantSize;
    private String shoeSize;
    private String shirtSize;
    private String jacketSize;

}
