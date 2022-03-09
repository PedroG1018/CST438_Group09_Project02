package com.group09.cst438_project2.backend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Thank you for visiting.");

        return "home";
    }

    @GetMapping("/registerUserPage")
    public String registerUserPage(Model model) {
        return "registerUserPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model) {
        return "logoutPage";
    }

    @GetMapping("/editItemPage")
    public String editItemPage(Model model) {
        return "editItemPage";
    }

    @GetMapping("/listPage")
    public String listPage(Model model) {
        return "listPage";
    }
}
