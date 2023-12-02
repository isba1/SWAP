package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FeedUserPost {

    private List<String> gcsUrls;

    private UUID postID;

    private UUID userID;

    private String userName;

    private String postName;

    private String postDescription;

    private String postCategory;

    private String postBrand;

    private String postStyle;

    private String postSize;

}
