package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.TradesOffered;
import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.DTO.UserProfileCompact;
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
    public UserProfile getMyProfile(@RequestParam(name = "UserID") String userID) {
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

    @GetMapping("/followers")
    @CrossOrigin("http://localhost:3000")
    public List<UserProfileCompact> getMyFollowers(@RequestParam(name = "userID") String userID) {
        return profileService.getMyFollowers(userID);
    }

    @GetMapping("/following")
    @CrossOrigin("http://localhost:3000")
    public List<UserProfileCompact> getMyFollowing(@RequestParam(name = "userID") String userID) {
        return profileService.getMyFollowing(userID);
    }

    @GetMapping("/singleFollower")
    @CrossOrigin("http://localhost:3000")
    public UserProfile getIndividualFollower(@RequestParam(name = "userID") String userID) {
        return profileService.getIndividualProfile(userID);
    }

    @GetMapping("/singleFollowing")
    @CrossOrigin("http://localhost:3000")
    public UserProfile getIndividualFollowing(@RequestParam(name = "userID") String userID) {
        return profileService.getIndividualProfile(userID);
    }





}
