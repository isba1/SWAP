package com.SwapToSustain.Server.Controller;

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
    @CrossOrigin(origins = "http://localhost:3000")
    public void saveAccountInfo(@RequestBody UserAccountInfo userAccountInfo) {
        newUserService.saveAccountInfo(userAccountInfo);
    }

    @PostMapping("/saveUserInterests")
    @CrossOrigin(origins = "http://localhost:3000")
    public void saveUserInterests(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestBody UserInterests userInterests) {
        newUserService.saveUserInterests(email, password, userInterests);
    }

}
