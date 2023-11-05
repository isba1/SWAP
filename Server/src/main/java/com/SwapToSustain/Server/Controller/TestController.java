package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.Model.UserAccountInfoModel;
import com.SwapToSustain.Server.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @GetMapping("/findUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public UserAccountInfoModel findUser(@RequestParam (name = "userID") String ID) {

        return userInfoRepository.findByUserID(UUID.fromString(ID));

    }


}
