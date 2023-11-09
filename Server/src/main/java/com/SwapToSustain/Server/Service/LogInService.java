package com.SwapToSustain.Server.Service;

import com.SwapToSustain.Server.Components.TokenInterface;
import com.SwapToSustain.Server.Converter.DTOConverter;
import com.SwapToSustain.Server.DTO.LoginAuthentication;
import com.SwapToSustain.Server.DTO.UserAccountInfo;
import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Model.UserPostModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import com.SwapToSustain.Server.Repository.UserPostRepository;
import com.SwapToSustain.Server.Util.TokenValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.SwapToSustain.Server.Config.LoginConfig.GENERAL_TOKEN_EXPIRATION;

@Service
public class LogInService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private UserPostRepository userPostRepository;

    @Autowired
    private TokenInterface tokenInterface;

    private UUID findUserID(String email, String password) {
        final UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByEmailAndPassword(email, password);

        return userAccountInfoModel.getUserID();
    }

    public LoginAuthentication loginAndTokenGeneration(boolean ret, String email, String password) {

        LoginAuthentication loginAuthentication = new LoginAuthentication();

        if (ret) {
            UUID foundAccount = findUserID(email, password);

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

    public LoginAuthentication newUserAndTokenGeneration(UserAccountInfo userAccountInfo) {
        saveAccountInfo(userAccountInfo);

        LoginAuthentication loginAuthentication = new LoginAuthentication();

        UUID foundAccount = findUserID(userAccountInfo.getEmail(), userAccountInfo.getPassword());

        loginAuthentication.setLoginSuccess(true);

        TokenValue tokenValue = new TokenValue(foundAccount, GENERAL_TOKEN_EXPIRATION);
        String token = tokenInterface.generateToken(tokenValue);

        loginAuthentication.setTokenString(token);

        return loginAuthentication;


    }

    public boolean userAuthentication(String email, String password){

        // use userInfoRepository method findByUsernameAndPassword
        final UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByEmailAndPassword(email, password);
        // if it returns null, then the account doesn't exist, return false
        // if it returns an account, then the account exists, return true
        return userAccountInfoModel != null;
    }

    public boolean saveAccountInfo(UserAccountInfo userAccountInfo){
        if (userInfoRepository.findByEmail(userAccountInfo.getEmail()) != null || userInfoRepository.findByUserName(userAccountInfo.getUserName()) != null) {
            return false;
        } else {
            UserAccountInfoModel userAccountInfoModel = new UserAccountInfoModel();

            dtoConverter.convertDTO(userAccountInfoModel, userAccountInfo);

            userInfoRepository.save(userAccountInfoModel);

            return true;
        }


    }

    public void deleteUser(String email, String password){
        UserAccountInfoModel userAccountInfoModel = userInfoRepository.findByEmailAndPassword(email, password);

        List<UserPostModel> allPosts = userPostRepository.findAllByUserID(userAccountInfoModel.getUserID());

        userPostRepository.deleteAll(allPosts);

        userInfoRepository.delete(userAccountInfoModel);

    }

}
