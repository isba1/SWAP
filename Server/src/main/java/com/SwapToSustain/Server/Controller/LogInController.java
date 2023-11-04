package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.Components.TokenInterface;
import com.SwapToSustain.Server.DTO.LoginAuthentication;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.Service.LogInService;
import com.SwapToSustain.Server.Util.TokenValue;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public LoginAuthentication userLogin(@RequestParam(name = "email") String email,
                                         @RequestParam(name = "password") String password) {
        LoginAuthentication loginAuthentication = new LoginAuthentication();

        boolean ret = logInService.userAuthentication(email, password);

        UUID foundAccount = logInService.findUserID(email, password);

        if (ret) {
            loginAuthentication.setLoginSuccess(ret);

            TokenValue tokenValue = new TokenValue(foundAccount, GENERAL_TOKEN_EXPIRATION);
            String token = tokenInterface.generateToken(tokenValue);

            loginAuthentication.setTokenString(token);

            return loginAuthentication;

        } else {
            loginAuthentication.setLoginSuccess(ret);
            loginAuthentication.setTokenString("");
            return loginAuthentication;
        }

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
