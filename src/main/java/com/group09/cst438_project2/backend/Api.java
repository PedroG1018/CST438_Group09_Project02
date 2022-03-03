package com.group09.cst438_project2.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class: Api.java
 * Last Modified: 03/02/2022
 * Description: API endpoints that will be used to fetch data from our database
 * Note: Setting up fake data for the time being
 */
@Controller
@RequestMapping(path = "/api")
public class Api {
    //@Autowired
    //private UserRepository userRepository;

    private List<User> fakeUsers = new ArrayList<>();
    private List<WishList> fakeLists = new ArrayList<>();
    private List<Item> fakeItems = new ArrayList<>();

    public void setFakeUsers() {
        fakeUsers.clear();

        User fakeAdmin = new User(1, "Fake Admin", "fakeAdmin", "fakeadminpassword", true);
        User fakeUser1 = new User(2, "Fake User 1", "fakeuser1", "fakepassword1", false);
        User fakeUser2 = new User(3, "Fake User 2", "fakeuser2", "fakepassword2", false);
        User fakeUser3 = new User(4, "Fake User 3", "fakeuser3", "fakepassword3", false);

        fakeUsers.add(fakeAdmin);
        fakeUsers.add(fakeUser1);
        fakeUsers.add(fakeUser2);
        fakeUsers.add(fakeUser3);
    }

    public void setFakeLists() {
        fakeLists.clear();

        WishList fakeList1 = new WishList(1, 1, "Fake List 1", "The admin's fake list");
        WishList fakeList2 = new WishList(2, 1, "Fake List 2", "The first fake list");
        WishList fakeList3 = new WishList(3, 2, "Fake List 3", "The second fake list");

        fakeLists.add(fakeList1);
        fakeLists.add(fakeList2);
        fakeLists.add(fakeList3);
    }

    //TODO: Set up fake items
    public void setFakeItems() {
        fakeItems.clear();

    }

    //TODO: Might be a good idea to put fake users in another file and make them static

    //TODO: Use real user data once database is set up
    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        // return userRepository.findAll();

        setFakeUsers();

        return fakeUsers;
    }

    //TODO: Use real user data once database is set up
    @PostMapping(path = "/addUser")
    public @ResponseBody String addUser (@RequestParam String name, @RequestParam String username, @RequestParam String password, @RequestParam Boolean isAdmin) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setAdmin(isAdmin);

        //userRepository.save(user);

        return "saved";
    }

    //TODO: Use real user data once database is set up
    @GetMapping(path = "/deleteUser")
    public @ResponseBody String deleteUser(@RequestParam (defaultValue = "1") String id) {
        setFakeUsers();

        for (User user : fakeUsers) {
            if (user.getUserId().toString().equals(id)) {
                return "deleted";
            }
        }
        return "User not found";
    }

    //TODO: Use real user data once database is set up
    @GetMapping(path = "/findByName")
    public @ResponseBody List<User> findUserByName(@RequestParam (defaultValue = "testuser") String name) {
        //return userRepository.findUserByName(name);

        setFakeUsers();

        List<User> foundUsers = new ArrayList<>();

        for (User user : fakeUsers) {
            if (user.getName().equals(name)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    //TODO: Need more user api endpoints and ones for wishlists and items
}
