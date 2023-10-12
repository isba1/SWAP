package com.SwapToSustain.Server.Controller.NewUser;

import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.Service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newUser")
public class NewUserController {

    @Autowired
    NewUserService newUserService;

    @PostMapping("/saveAccountInfo")
    public void saveAccountInfo(@RequestBody UserAccountInfo userAccountInfo) {
        newUserService.saveAccountInfo(userAccountInfo);
    }

    @PostMapping("/saveUserInterests")
    public void saveUserInterests(
            @RequestParam String username,
            @RequestParam String password,
            @RequestBody UserInterests userInterests) {
        newUserService.saveUserInterests(username, password, userInterests);
    }

}
