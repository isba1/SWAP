package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.NewUserPost;
import com.SwapToSustain.Server.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/newPostInfo")
    @CrossOrigin(origins = "http://localhost:3000")
    public void newPostInfo(@RequestBody NewUserPost newUserPost,
                        @RequestParam(name = "userID") String userName, @RequestParam(name = "postID") String postID) {
        postService.saveUserPostInfo(newUserPost, userName, postID);
    }

    @PostMapping(value = "/newPostImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin(origins = "http://localhost:3000")
    public String newPostImage (@RequestParam(name = "image") MultipartFile image) throws IOException {
        return postService.saveUserPostImages(image);
    }

    // need to figure out how to remove images from GCS as well
    @PostMapping(value = "/removePost")
    @CrossOrigin(origins = "http://localhost:3000")
    public void removePost(@RequestParam(name = "postID") String postID) {
        postService.removeUserPost(postID);
    }

}
