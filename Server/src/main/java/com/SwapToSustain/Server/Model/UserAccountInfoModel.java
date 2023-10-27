package com.SwapToSustain.Server.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Document(collection = "UserInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountInfoModel {

    @Id
    private ObjectId userID = ObjectId.get(); // unique id used by the DB

    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String state;
    private String zipCode;
    private List<String> interestCategory;
    private List<String> interestBrand;
    private HashSet<ObjectId> following;
    private HashSet<ObjectId> followers;
    private String pantSize;
    private String shoeSize;
    private String shirtSize;
    private String jacketSize;

}
