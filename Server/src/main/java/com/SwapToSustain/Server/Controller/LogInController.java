package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.LoginAuthentication;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LogInController {

    @Autowired
    LogInService logInService;



    @GetMapping("/reoccurringUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public LoginAuthentication userLogin(@RequestParam(name = "email") String email,
                                         @RequestParam(name = "password") String password) {

        boolean ret = logInService.userAuthentication(email, password);

        return logInService.loginAndTokenGeneration(ret, email, password);

    }

    @PostMapping("/saveAccountInfo")
    @CrossOrigin(origins = "http://localhost:3000")
    public LoginAuthentication saveAccountInfo(@RequestBody UserAccountInfo userAccountInfo) {

        return logInService.newUserAndTokenGeneration(userAccountInfo);

    }

    @DeleteMapping("/deleteUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteUser(@RequestParam(name = "email") String email,
                           @RequestParam(name = "password") String password){
        logInService.deleteUser(email, password);
    }

}
