package com.group09.cst438_project2.backend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
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
        return "loginPage";
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
