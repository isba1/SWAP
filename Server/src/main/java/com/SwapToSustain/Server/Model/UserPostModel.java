package com.SwapToSustain.Server.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private List<String> base64Images;

    private String postName;

    private String postDescription;

    private String postCategory;

    private String postBrand;

    private String postStyle;

    private String postSize;

}
