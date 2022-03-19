package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/"})
public class WishListController {

    @Autowired
    Api api;

    @GetMapping("/lists")
    public String allLists(Model model) {
        return "landingPage";
    }

    @GetMapping("/items")
    public String listItems(@RequestParam Integer userId, @RequestParam Integer listId, Model model) {
        User user = api.getUser(userId);

        return "listPage";
    }

    @DeleteMapping("/items")
    public String deleteList() {

        return "landingPage";
    }
}
