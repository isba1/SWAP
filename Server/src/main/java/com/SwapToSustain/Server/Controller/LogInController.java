package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LogInController {

    @Autowired
    LogInService logInService;

    @GetMapping("/reoccurringUser")
    public boolean userLogin(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password) {

        return logInService.userAuthentication(username, password);

    }


    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password){

    }

}
