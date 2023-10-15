package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/newPost")
    public void newPost(@RequestBody UserPost userPost) {
        postService.saveUserPost(userPost);
    }

}
