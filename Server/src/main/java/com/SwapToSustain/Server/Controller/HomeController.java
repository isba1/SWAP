package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.FeedUserPost;
import com.SwapToSustain.Server.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping
    public List<FeedUserPost> getRecommendedFeed(@RequestParam(name = "userID") String userID) {
        return homeService.getRecommendedFeed(userID);
    }

}
