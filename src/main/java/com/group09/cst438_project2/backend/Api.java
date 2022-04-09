package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Class: Api.java
 * Last Modified: 03/23/2022
 * Description: API endoints for accessing the tables and values in our database.
 *              Can be used as routes on the app or via Postman
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

    // api endpoint to show every user's lists
    @GetMapping(path = "/allLists")
    public @ResponseBody Iterable<WishList> getAllWishLists() {
        return wishListRepository.findAll();
    }

    // api endpoint to show every user's items
    @GetMapping(path = "/allItems")
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
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

        return "user saved";
    }

    // api endpoint to delete a specific user
    @DeleteMapping(path = "/deleteUser")
    public @ResponseBody String deleteUser (@RequestParam Integer userId) {
        User user = userRepository.findDistinctByUserIdLike(userId);

        userRepository.delete(user);

        return "user deleted";
    }

    // api endpoint to show all of a user's lists
    @GetMapping(path = "/lists")
    public @ResponseBody Iterable<WishList> getUserLists(@RequestParam Integer userId) {
        return wishListRepository.findWishListsByUserIdLike(userId);
    }

    // api endpoint to retrieve a specific wish list
    @GetMapping(path = "/list")
    public @ResponseBody WishList getWishList(@RequestParam Integer listId) {
        return wishListRepository.findDistinctByListIdLike(listId);
    }

    // api endpoint to add a new wish list
    @PostMapping(path = "/addList")
    public @ResponseBody String addList(@RequestParam Integer userId, @RequestParam String listName, @RequestParam String description) {
        WishList wishList = new WishList();

        wishList.setUserId(userId);
        wishList.setListName(listName);
        wishList.setDescription(description);

        wishListRepository.save(wishList);

        return "wish list saved";
    }

    // api endpoint to delete a specific wish list
    @DeleteMapping(path = "/deleteList")
    public @ResponseBody String deleteList(@RequestParam Integer listId) {
        WishList wishList = wishListRepository.findDistinctByListIdLike(listId);

        wishListRepository.delete(wishList);

        return "wish list deleted";
    }

    // api endpoint to delete all of a user's wish lists
    @DeleteMapping(path = "deleteLists")
    public @ResponseBody String deleteLists(@RequestParam Integer userId) {
        Iterable<WishList> wishLists = wishListRepository.findWishListsByUserIdLike(userId);

        wishListRepository.deleteAll(wishLists);

        return "wish lists deleted";
    }

    // api endpoint to delete a specific item
    @DeleteMapping(path = "/deleteItem")
    public @ResponseBody String deleteItem(@RequestParam Integer itemId) {
        Item item = itemRepository.findDistinctByItemIdLike(itemId);

        itemRepository.delete(item);

        return "item deleted";
    }

    // api endpoint to delete all items in a specific list
    @DeleteMapping(path = "/deleteListItems")
    public @ResponseBody String deleteListItems(@RequestParam Integer listId) {
        Iterable<Item> items = itemRepository.findItemsByListIdLike(listId);

        itemRepository.deleteAll(items);

        return "all list items deleted";
    }

    // api endpoint to delete all of a user's items
    @DeleteMapping(path = "/deleteAllItems")
    public @ResponseBody String deleteAllItems(@RequestParam Integer userId) {
        Iterable<Item> items = itemRepository.findItemsByUserIdLike(userId);

        itemRepository.deleteAll(items);

        return "all user items deleted";
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

        return "item saved";
    }

    // api endpoint to find a user by their unique id
    @GetMapping(path = "/findByItemId")
    public @ResponseBody Item getItem(@RequestParam Integer itemId) {
        if (itemRepository.findById(itemId).isPresent()) {
            return itemRepository.findById(itemId).get();
        } else {
            return new Item();
        }
    }

    @PostMapping(path = "/updateItem")
    public @ResponseBody String addItem(@RequestParam Item item) {

        itemRepository.save(item);

        return "item saved";
    }
}
