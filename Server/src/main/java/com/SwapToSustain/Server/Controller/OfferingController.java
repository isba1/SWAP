package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.PersonalUserPost;
import com.SwapToSustain.Server.Service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferingController {

    @Autowired
    OfferingService offeringService;

    // this it the endpoint that will be hit once user finalized their offer
    // It will send the seller information for the post ID they're interested in and the user ID for whom the post
    // belongs too after they have clicked the post they're making an offer on. And it will send the buyer's information
    // for the post ID they're offering and their user ID (coming from session id) once they've selected their post they
    // want to offer for trade from the list of their own posts generated from /itemsToOffer endpoint
    @PostMapping("/makeOffer")
    @CrossOrigin("http://localhost:3000")
    public void makeOffer(@RequestParam(name = "sellerPostID") String sellerPostID,
                          @RequestParam(name = "sellerUserID") String sellerUserID,
                          @RequestParam(name = "buyerPostID") String buyerPostID,
                          @RequestParam(name = "buyerUserID") String buyerUserID) {

        offeringService.makeOffer(buyerPostID, buyerUserID, sellerPostID, sellerUserID);

    }

    // this is the endpoint that will be hit when user is searching for what items they have to offer when they first click
    // the offer button. It will display all of their items that they can offer to trade
    @GetMapping("/itemsToOffer")
    @CrossOrigin("http://localhost:3000")
    public List<PersonalUserPost> getItemsToOffer(@RequestParam(name = "userID") String userID) {

        return offeringService.getItemsToOffer(userID);

    }


    // end point hit if the offer is accepted
    @PostMapping("/acceptOffer")
    @CrossOrigin("http://localhost:3000")
    public void acceptOffer() {

    }




}
