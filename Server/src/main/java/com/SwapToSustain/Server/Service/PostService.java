package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.NewUserPost;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PostService {

    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private DTOConverter dtoConverter;

    public void saveUserPost(NewUserPost newUserPost, String objectID) {

        UserPostModel userPostModel = new UserPostModel();

        final ObjectId realID = new ObjectId(objectID);

        dtoConverter.convertDTO(userPostModel, newUserPost, realID);

        userPostRepository.save(userPostModel);

    }


}
