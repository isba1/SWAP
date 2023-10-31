package com.SwapToSustain.Server.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserProfile {

    private String userName;

    private Integer followersCount;

    private Integer followingCount;

    private List<PersonalUserPost> personalUserPosts;

}
