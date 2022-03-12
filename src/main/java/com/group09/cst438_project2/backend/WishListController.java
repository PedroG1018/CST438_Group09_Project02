package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/"})
public class WishListController {

    @Autowired
    Api api;

    @GetMapping("/items")
    public String listItems(@RequestParam Integer listId, @RequestParam Integer userId, Model model) {
        User user = api.getUser(userId);

        return "listPage";
    }
}
