package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TradesOffered {

    private List<String> myGcsUrls;

    private UUID myPostID;

    private String myName;

    private String myPostDescription;

    private String myPostCategory;

    private String myPostBrand;

    private String myPostStyle;

    private String myPostSize;

    private List<String> theirGcsUrls;

    private UUID theirPostID;

    private UUID theirUserID;

    private String theirUserName;

    private String theirPostName;

    private String theirPostDescription;

    private String theirPostCategory;

    private String theirPostBrand;

    private String theirPostStyle;

    private String theirPostSize;



}
