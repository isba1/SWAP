package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserPost {

    private byte[] imageBinary;

    private String postDescription;

    private List<String> postCategories;

}
