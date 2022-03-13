package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/home"})
public class IndexController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    Api api;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Thank you for visiting.");

        return "home";
    }

    // get request for register user page
    @GetMapping(value="/registerUser")
    public String registerUserForm(Model model) {
        model.addAttribute("user", new User());
        return "registerUserPage";
    }

    // post request for register user page
    @PostMapping(value="/registerUser")
    public String registerUserSubmit(@ModelAttribute User user, Model model) {
        User u = api.getUser(user.getUsername());

        // check if username is already in use
        if (u != null) {
            model.addAttribute("usernameTaken", true);
            return "registerUserPage";
        }
        // otherwise save user to database
        api.addUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());

        // return to login
        return "home";
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
