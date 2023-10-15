package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class PostService {

    @Autowired
    UserPostRepository userPostRepository;
    DTOConverter dtoConverter;

    public void saveUserPost(UserPost userPost) {

        UserPostModel userPostModel = new UserPostModel();

        dtoConverter.convertDTO(userPostModel, userPost);

        userPostRepository.save(userPostModel);

    }


}
