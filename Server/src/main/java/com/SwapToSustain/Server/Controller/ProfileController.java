package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;
    
    @GetMapping("/myProfile")
    @CrossOrigin("http://localhost:3000")
    public UserProfile getProfile(@RequestParam(name = "UserID") String userID) {
        return profileService.getUserProfileInfo(userID);
    }


}
