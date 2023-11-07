package com.SwapToSustain.Server.Controller;

import com.SwapToSustain.Server.DTO.UserProfileSearch;
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
     public List<UserProfileSearch> getUsers(@RequestParam(name = "userName") String userName,
                                             @RequestParam(name = "shirtSize") String shirtSize,
                                             @RequestParam(name = "shoeSize") String shoeSize,
                                             @RequestParam(name = "jacketSize") String jacketSize,
                                             @RequestParam(name = "pantSize") String pantSize,
                                             @RequestParam(name = "interestBrand") String interestBrand,
                                             @RequestParam(name = "interestStyle") String interestStyle) throws IllegalAccessException {

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




}
