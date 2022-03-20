package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/"})
public class WishListController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    Api api;

    @RequestMapping("/lists")
    public String allLists(@RequestParam Integer userId, Model model) {

        return "landingPage";
    }

    @GetMapping("/items")
    public String listItems(@RequestParam Integer listId, Model model) {
        String uri = BASE_URI + "items?listId=" + listId;
        RestTemplate restTemplate = new RestTemplate();

        Item[] items = restTemplate.getForObject(uri, Item[].class);
        WishList wishList = api.getWishList(listId);

        model.addAttribute("items", items);
        model.addAttribute("wishList", wishList);

        return "listPage";
    }

    @GetMapping("/deleteList")
    public String deleteList(@RequestParam Integer listId) {
        Integer userId = api.getWishList(listId).getUserId();

        api.deleteItems(listId);
        api.deleteList(listId);

        return "redirect:/lists?userId=" + userId;
    }
}
