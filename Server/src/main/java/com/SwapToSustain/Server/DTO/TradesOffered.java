package com.SwapToSustain.Server.DTO;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class TradesOffered {

    private List<String> myBase64Images;

    private ObjectId myPostID;

    private String myName;

    private String myPostDescription;

    private String myPostCategory;

    private String myPostBrand;

    private String myPostStyle;

    private String myPostSize;

    private List<String> theirBase64Images;

    private ObjectId theirPostID;

    private ObjectId theirUserID;

    private String theirUserName;

    private String theirPostDescription;

    private String theirPostCategory;

    private String theirPostBrand;

    private String theirPostStyle;

    private String theirPostSize;



}
