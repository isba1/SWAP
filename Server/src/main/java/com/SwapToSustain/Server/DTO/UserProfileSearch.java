package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UserProfileSearch {

    private UUID userID;

    private String userName;

    private Integer followersCount;

    private Integer followingCount;

}
