package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserProfileCompact;
import com.SwapToSustain.Server.Service.FollowerService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowerController {

    @Autowired
    FollowerService followerService;

    @PutMapping("/add")
    @CrossOrigin("http://localhost:3000")
    public void addFollowing(@RequestParam(name = "loginUserName") String loginUserName,
                                   @RequestParam(name = "userNameToFollow") String userNameToFollow) {
        followerService.addFollower(loginUserName, userNameToFollow);
    }

    @PutMapping("/remove")
    @CrossOrigin("http://localhost:3000")
    public void removeFollowing(@RequestParam(name = "loginUserName") String loginUserName,
                                @RequestParam(name = "userNameToRemoveFollow") String userNameToRemoveFollow) {
        followerService.removeFollowing(loginUserName, userNameToRemoveFollow);
    }
}
