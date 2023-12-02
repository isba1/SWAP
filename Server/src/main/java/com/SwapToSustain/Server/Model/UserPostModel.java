package com.SwapToSustain.Server.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Document(collection = "UserPosts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPostModel {

    @Id
    private UUID postID = UUID.randomUUID();

    private UUID userID;

    private String userName;

    private List<String> gcsUrls;

    private String postName;

    private String postDescription;

    private String postCategory;

    private String postBrand;

    private String postStyle;

    private String postSize;

    private HashSet<String> connectedUsers;

}
