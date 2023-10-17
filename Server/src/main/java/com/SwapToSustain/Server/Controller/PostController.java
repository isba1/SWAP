package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/newPost")
    public void newPost(@RequestBody UserPost userPost,
                        @RequestParam(name = "UserUUID") UUID uuid) {
        postService.saveUserPost(userPost, uuid);
    }

}
