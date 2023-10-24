package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.Service.NewUserInterestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newUserInterests")
public class NewUserInterestsController {

    @Autowired
    NewUserInterestsService newUserService;

    @PostMapping("/saveUserInterests")
    @CrossOrigin(origins = "http://localhost:3000")
    public void saveUserInterests(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestBody UserInterests userInterests) {
        newUserService.saveUserInterests(email, password, userInterests);
    }

}
