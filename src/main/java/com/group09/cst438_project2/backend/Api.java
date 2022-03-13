package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class Api {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/findByUsername")
    public @ResponseBody User getUser(@RequestParam String username) {
        return userRepository.findDistinctByUsernameLike(username);
    }

    @PostMapping(path="/addUser")
    public @ResponseBody String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setAdmin(false);

        userRepository.save(user);

        return "saved";
    }

//    @GetMapping(path = "/deleteUser")
//    public @ResponseBody String deleteUser (@RequestParam String id) {
//        getAllUsers();
//
//        for(User user: userRepository){
//            if(user.getUserId().toString().equals(id)) {
//                return "Selected User A/C has been deleted!";
//            }
//        }
//        return "Sorry! The selected User A/C is not found in the database!";
//    }

}
