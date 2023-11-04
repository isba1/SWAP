package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserInterests;
import com.SwapToSustain.Server.Service.NewUserInterestsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/newUserInterests")
public class NewUserInterestsController {

    @Autowired
    NewUserInterestsService newUserService;

    @PostMapping("/saveUserInterests")
    @CrossOrigin(origins = "http://localhost:3000")
    public void saveUserInterests(
            @RequestParam(name = "userID") UUID userID,
            @RequestBody UserInterests userInterests) {
        newUserService.saveUserInterests(userID, userInterests);
    }

}
