package com.SwapToSustain.Server.Service;

import org.springframework.stereotype.Service;

@Service
public class LogInService {

    public boolean userAuthentication(String username, String password){

        // use userInfoRepository method findByUsernameAndPassword

        // if it returns null, then the account doesn't exist, return false

        // if it returns an account, then the account exists, return true

    }

}
