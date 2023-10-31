package com.SwapToSustain.Server.DTO;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;


// this will be used for the posts for the feed because I need the postID for when making offers
@Data
public class PersonalUserPost {

    private List<String> base64Images;

    private ObjectId postID;

    private String name;

    private String postDescription;

    private String postCategory;

    private String postBrand;

    private String postStyle;

    private String postSize;

}
