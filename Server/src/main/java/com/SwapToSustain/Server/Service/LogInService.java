package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public boolean userAuthentication(String username, String password){

        // use userInfoRepository method findByUsernameAndPassword
        final UserAccountInfoModel userAcct = userInfoRepository.findByUsernameAndPassword(username, password);
        // if it returns null, then the account doesn't exist, return false
        // if it returns an account, then the account exists, return true
        return userAcct != null;
    }

}
