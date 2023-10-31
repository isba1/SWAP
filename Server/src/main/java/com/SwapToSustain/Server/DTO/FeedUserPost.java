package com.SwapToSustain.Server.DTO;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class FeedUserPost {

    private List<String> base64Images;

    private ObjectId postID;

    private ObjectId userID;

    private String name;

    private String postDescription;

    private String postCategory;

    private String postBrand;

    private String postStyle;

    private String postSize;

}
