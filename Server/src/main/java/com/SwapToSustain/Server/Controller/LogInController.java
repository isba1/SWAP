package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.Components.TokenInterface;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.Service.LogInService;
import com.SwapToSustain.Server.Util.TokenValue;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import static com.SwapToSustain.Server.Components.LoginFilter.AUTHORIZATION_HEADER_KEY;
import static com.SwapToSustain.Server.Config.LoginConfig.GENERAL_TOKEN_EXPIRATION;

@RestController
@RequestMapping("/login")
public class LogInController {

    @Autowired
    LogInService logInService;

    @Autowired
    private TokenInterface tokenInterface;

    @GetMapping("/reoccurringUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public String userLogin(@RequestParam(name = "email") String email,
                            @RequestParam(name = "password") String password) {
        boolean ret = logInService.userAuthentication(email, password);
        if (ret) {
            TokenValue tokenValue = new TokenValue(email, GENERAL_TOKEN_EXPIRATION);
            return tokenInterface.generateToken(tokenValue);
        }
        return "";

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
