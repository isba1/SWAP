package com.SwapToSustain.Server.POJO;

import lombok.Data;

import java.util.List;

@Data
public class UserInterests {

    private List<String> brands;

    private List<String> clothingCategory;

    private String shirtSize;

    private String shoeSize;

    private String pantSize;

}
