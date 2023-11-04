package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.NewUserPost;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private DTOConverter dtoConverter;

    public void saveUserPost(NewUserPost newUserPost, String postID) {

        UserPostModel userPostModel = new UserPostModel();

        final UUID realID = UUID.fromString(postID);

        dtoConverter.convertDTO(userPostModel, newUserPost, realID);

        userPostRepository.save(userPostModel);

    }


}
