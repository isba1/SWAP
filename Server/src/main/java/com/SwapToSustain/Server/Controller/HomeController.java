package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.FeedUserPost;
import com.SwapToSustain.Server.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping
    @CrossOrigin("http://localhost:3000")
    public List<FeedUserPost> getRecommendedFeed(@RequestParam(name = "userID") String userName) {
        return homeService.getRecommendedFeed(userName);
    }

    @GetMapping("/exploreTops")
    @CrossOrigin("http://localhost:3000")
    public List<FeedUserPost> getExploreTops(@RequestParam(name = "userID") String userName) {
        return homeService.exploreCategories(userName, "Tops");
    }

    @GetMapping("/exploreBottoms")
    @CrossOrigin("http://localhost:3000")
    public List<FeedUserPost> getExploreBottoms(@RequestParam(name = "userID") String userName) {
        return homeService.exploreCategories(userName, "Bottoms");
    }

    @GetMapping("/exploreOuterwear")
    @CrossOrigin("http://localhost:3000")
    public List<FeedUserPost> getExploreOuterwear(@RequestParam(name = "userID") String userName) {
        return homeService.exploreCategories(userName, "Outerwear");
    }

    @GetMapping("/exploreShoes")
    @CrossOrigin("http://localhost:3000")
    public List<FeedUserPost> getExploreShoes(@RequestParam(name = "userID") String userName) {
        return homeService.exploreCategories(userName, "Shoes");
    }

    @GetMapping("/exploreAccessories")
    @CrossOrigin("http://localhost:3000")
    public List<FeedUserPost> getExploreAccessories(@RequestParam(name = "userID") String userName) {
        return homeService.exploreCategories(userName, "Accessories");
    }

}
