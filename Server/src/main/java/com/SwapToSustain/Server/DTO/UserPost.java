package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;


// this will be used for the posts for the feed because I need the postID for when making offers
@Data
public class UserPost {

    private List<String> base64Images;

    private UUID postID;

    private String name;

    private String postDescription;

    private String postCategory;

    private String postBrand;

    private String postStyle;

    private String postSize;

}
