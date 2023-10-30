package com.SwapToSustain.Server.Controller;

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
    public boolean userLogin(@RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password) {

        return logInService.userAuthentication(email, password);

    }

    @PostMapping("/saveAccountInfo")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean saveAccountInfo(@RequestBody UserAccountInfo userAccountInfo) {
        return logInService.saveAccountInfo(userAccountInfo);
    }

    @DeleteMapping("/deleteUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteUser(@RequestParam(name = "email") String email,
                           @RequestParam(name = "password") String password){
        logInService.deleteUser(email, password);
    }

}
