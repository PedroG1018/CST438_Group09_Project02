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

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Thank you for visiting.");

        return "home";
    }

    @RequestMapping(value = "/loginForm")
    public String loginForm(Model model){
        model.addAttribute("user", new User());
        return "home";
    }

    @ModelAttribute(value = "loginForm")
    @PostMapping("/loginForm")
    public @ResponseBody String loginSubmit(@ModelAttribute User user){
        User u = userRepository.findDistinctByUsernameLike(user.getUsername());
        if (u == null){
            return "Wrong login name";
        }
        if(u.getPassword().equals(user.getPassword())){
            return "Correct password";
        } else {
            return "Incorrect password";
        }
    }

    @GetMapping(value = "/userIsTaken")
    public @ResponseBody boolean checkForUser(String username){
        User u = userRepository.findDistinctByUsernameLike(username);
        return u != null;
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
        return "redirect:/";
    }

    // get request for add item page
    @GetMapping(value="/addItem")
    public String addItemForm(@RequestParam Integer listId, @RequestParam Integer userId, Model model) {
        // adding empty item object and id values for thymeleaf to use when submitting post request form
        // userId and listId already initialized
        model.addAttribute("item", new Item(userId, listId, "", "", "", ""));

        return "addItemPage";
    }

    @PostMapping(value="/addItem")
    public String addItemSubmit(@ModelAttribute Item item, Model model) {
        // check that item name field is filled out
        if (item.getItemName() == null || item.getItemName().equals("")) {
            model.addAttribute("nameFieldEmpty", true);
            return "addItemPage";
        }
        // otherwise save item to the database
        api.addItem(item.getListId(), item.getUserId(), item.getItemName(), item.getItemURL(), item.getImgURL(), item.getDescription());

        // return to items page
        return "redirect:items?listId=" + item.getListId() + "&userId=" + item.getUserId();
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model) {
        return "logoutPage";
    }

    @GetMapping("/editItemPage")
    public String editItemPage(Model model) {
        return "editItemPage";
    }
}
