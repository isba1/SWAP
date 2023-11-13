package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserProfile;
import com.SwapToSustain.Server.DTO.UserProfileCompact;
import com.SwapToSustain.Server.DTO.UserSearchCriteria;
import com.SwapToSustain.Server.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

     @Autowired
     private SearchService searchService;

     @GetMapping("/searchUsers")
     @CrossOrigin("http://localhost:3000")
     public List<UserProfileCompact> getUsers(@RequestParam(name = "userName", required = false) String userName,
                                              @RequestParam(name = "shirtSize", required = false) String shirtSize,
                                              @RequestParam(name = "shoeSize", required = false) String shoeSize,
                                              @RequestParam(name = "jacketSize", required = false) String jacketSize,
                                              @RequestParam(name = "pantSize", required = false) String pantSize,
                                              @RequestParam(name = "interestBrand", required = false) String interestBrand,
                                              @RequestParam(name = "interestStyle", required = false) String interestStyle) {
          UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
          userSearchCriteria.setUserName(userName);
          userSearchCriteria.setShirtSize(shirtSize);
          userSearchCriteria.setShoeSize(shoeSize);
          userSearchCriteria.setJacketSize(jacketSize);
          userSearchCriteria.setPantSize(pantSize);
          userSearchCriteria.setInterestBrand(interestBrand);
          userSearchCriteria.setInterestStyle(interestStyle);


          return searchService.findUsers(userSearchCriteria);
     }


     @GetMapping("/singleUser")
     @CrossOrigin("http://localhost:3000")
     public UserProfile getSingleUser(@RequestParam(name = "userID") String userID) {
          return searchService.getSingleUser(userID);
     }





}
