package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Class: Api.java
 * Last Modified: 03/11/2022
 * Description:
 */
@Controller
@RequestMapping(path="/api")
public class Api {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private ItemRepository itemRepository;

    // api endpoint to show all currently registered users
    @GetMapping(path = "/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // api endpoint t find a user by their unique username
    @GetMapping(path = "/findByUsername")
    public @ResponseBody User getUser(@RequestParam String username) {
        return userRepository.findDistinctByUsernameLike(username);
    }

    // api endpoint to find a user by their unique id
    @GetMapping(path = "/findByUserId")
    public @ResponseBody User getUser(@RequestParam Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        } else {
            return new User();
        }
    }

    // api endpoint to register a user into the database
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


    @GetMapping(path = "/deleteUser")
    public @ResponseBody String deleteUser (@RequestParam Integer id) {
        return userRepository.deleteDistinctByUserId(id).toString();

//        User user = userRepository.findById(id).get();

    }


    // api endpoint to show all of a user's lists
    @GetMapping(path = "/lists")
    public @ResponseBody Iterable<WishList> getUserLists(@RequestParam Integer userId) {
        return wishListRepository.findWishListsByUserIdLike(userId);
    }

    // api endpoint to add a new wish list
    @PostMapping(path = "/addList")
    public @ResponseBody String addList(@RequestParam Integer userId, @RequestParam String listName, @RequestParam String description) {
        WishList wishList = new WishList();

        wishList.setUserId(userId);
        wishList.setListName(listName);
        wishList.setDescription(description);

        wishListRepository.save(wishList);

        return "saved";
    }

    // api endpoint to show all items in a specific list
    @GetMapping(path = "/items")
    public @ResponseBody Iterable<Item> getListItems(@RequestParam Integer listId) {
        return itemRepository.findItemsByListIdLike(listId);
    }

    // api endpoint to add an item to a specific list
    @PostMapping(path = "/addItem")
    public @ResponseBody String addItem(@RequestParam Integer listId, @RequestParam Integer userId, @RequestParam String itemName, @RequestParam String itemURL, @RequestParam String imgURL, @RequestParam String description) {
        Item item = new Item();

        item.setListId(listId);
        item.setUserId(userId);
        item.setItemName(itemName);
        item.setItemURL(itemURL);
        item.setImgURL(imgURL);
        item.setDescription(description);

        itemRepository.save(item);

        return "saved";
    }
}
