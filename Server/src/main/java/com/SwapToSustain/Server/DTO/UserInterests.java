package com.SwapToSustain.Server.DTO;

import java.util.List;

import lombok.Data;

@Data
public class UserInterests {

    private List<String> brands;

    private List<String> clothingCategory;

    private String shirtSize;

    private String shoeSize;

    private String pantSize;

    private String jacketSize;

}