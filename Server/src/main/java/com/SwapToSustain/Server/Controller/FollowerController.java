package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.Service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowerController {

    @Autowired
    FollowerService followerService;

    @GetMapping("/add")
    @CrossOrigin("http://localhost:3000")
    public void addFollowing(@RequestParam(name = "loginUserName") String loginUserName,
                                   @RequestParam(name = "userNameToFollow") String userNameToFollow) {
        followerService.addFollower(loginUserName, userNameToFollow);
    }

    @GetMapping("/remove")
    @CrossOrigin("http://localhost:3000")
    public void removeFollowing(@RequestParam(name = "loginUserName") String loginUserName,
                                @RequestParam(name = "userNameToRemoveFollow") String userNameToRemoveFollow) {
        followerService.removeFollowing(loginUserName, userNameToRemoveFollow);
    }

}
