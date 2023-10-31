package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.TradesOffered;
import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    // this queries all the offers user has for their own product
    @GetMapping("/offersReceived")
    @CrossOrigin("http://localhost:3000")
    public List<TradesOffered> getReceivedOffers(@RequestParam(name = "UserID") String userID) {
        return profileService.getTradesOfferedMe(userID);
    }


    @GetMapping("/offersSent")
    @CrossOrigin("http://localhost:3000")
    public List<TradesOffered> getOffersSent(@RequestParam(name = "UserID") String userID) {
        return profileService.getTradesIOffer(userID);
    }

}
