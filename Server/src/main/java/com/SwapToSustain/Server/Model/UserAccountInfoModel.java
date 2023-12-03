package com.SwapToSustain.Server.Model;

import com.SwapToSustain.Server.DTO.UserNotification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

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
    private List<String> interestStyle;
    private List<String> interestBrand;
    private HashSet<UUID> following;
    private HashSet<UUID> followers;

    //first objectID is item yours (seller), and second one is the item that someone else has offered to trade (buyer)
    private HashMap<UUID, ArrayList<UUID>> offeredMe;

    //first objectID is the item that I am interested in (seller), and the second one is the item that I am offering (buyer)
    private HashMap<UUID, UUID> myOffers;

    private String pantSize;
    private String shoeSize;
    private String shirtSize;
    private String jacketSize;
    private List<UserNotification> notifications = new ArrayList<>();
}
