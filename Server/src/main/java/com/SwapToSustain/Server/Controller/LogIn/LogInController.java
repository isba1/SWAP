package com.SwapToSustain.Server.Controller.LogIn;

@RestController
@RequestMapping("/login")
public class LogInController {

    @PostMapping("/newUser")
    public void createUser(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password) {

    }

    @GetMapping("/reoccurringUser")
    public void userLogin(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password) {

    }


    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password){

    }

}
