package com.SwapToSustain.Server.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
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
    private ObjectId postID = ObjectId.get();

    private ObjectId userID;

    private String base64Image;

    private String postDescription;

    private List<String> postCategories;

}
