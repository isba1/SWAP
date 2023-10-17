package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private UserPostRepository userPostRepository;
    DTOConverter dtoConverter;

    public void saveUserPost(UserPost userPost, UUID uuid) {

        UserPostModel userPostModel = new UserPostModel();

        dtoConverter.convertDTO(userPostModel, userPost, uuid);

        userPostRepository.save(userPostModel);

    }


}
