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
     public List<UserProfileSearch> getUsers(@RequestBody UserSearchCriteria userSearchCriteria) throws IllegalAccessException {
          return searchService.findUsers(userSearchCriteria);
     }




}
