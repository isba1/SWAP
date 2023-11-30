package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.List;

@Data
public class NewUserPost {

    // private byte[][] imageBinary;
//    private List<String> base64Images;

    private String name;

    private String postDescription;

    private String postCategory;

    private String postBrand;

    private String postStyle;

    private String postSize;

}
