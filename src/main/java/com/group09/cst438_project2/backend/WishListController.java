package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

/**
 * Class: WishListController.java
 * Last Modified: 03/23/2022
 * Description: Controller class containing various routes related to user's wish lists
 */
@Controller
@RequestMapping({"/"})
public class WishListController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    Api api;

    private boolean validateSession(HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        return user == null;
    }

    // endpoint for seeing all of the user's wish lists; the landing page after logging in
    @RequestMapping("/lists")
    public String allLists(@RequestParam Integer userId, HttpSession session, Model model) {
        if (validateSession(session)) {
            return "redirect:/";
        }
        String uri = BASE_URI + "findByUserId?userId=" + userId;
        RestTemplate restTemplate = new RestTemplate();

        User user = restTemplate.getForObject(uri, User.class);
        Boolean admin = user.getAdmin();

        model.addAttribute("isAdmin", admin);
        return "landingPage";
    }

    // endpoint for seeing all the items in a wish list
    @GetMapping("/items")
    public String listItems(@RequestParam Integer listId, HttpSession session, Model model) {
        if (validateSession(session)) {
            return "redirect:/";
        }

        String uri = BASE_URI + "items?listId=" + listId;
        RestTemplate restTemplate = new RestTemplate();

        Item[] items = restTemplate.getForObject(uri, Item[].class);
        WishList wishList = api.getWishList(listId);

        User user = (User) session.getAttribute("USER_SESSION");
        Boolean admin = user.getAdmin();

        model.addAttribute("isAdmin", admin);
        model.addAttribute("items", items);
        model.addAttribute("wishList", wishList);

        return "listItemsPage";
    }

    // endpoint for deleting a wish list
    @GetMapping("/deleteList")
    public String deleteList(@RequestParam Integer listId) {
        Integer userId = api.getWishList(listId).getUserId();

        // deletes wish list and items within that wish list
        api.deleteListItems(listId);
        api.deleteList(listId);

        // redirect to all the user's wish lists
        return "redirect:/lists?userId=" + userId;
    }
}
