package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserPost;
import com.SwapToSustain.Server.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/newPost")
    @CrossOrigin(origins = "http://localhost:3000")
    public void newPost(@RequestBody UserPost userPost,
                        @RequestParam(name = "UserID") String objectID) throws IOException {
        postService.saveUserPost(userPost, objectID);
    }

}
