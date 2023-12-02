package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserProfile {

    private UUID userID;

    private String userName;

    private String contactNumber;

    private Integer followersCount;

    private Integer followingCount;

    private List<UserPost> userPosts;

    private String userCity;

    private String userState;

}
