package com.group09.cst438_project2.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class: IndexController.java
 * Last Modified: 03/23/2022
 * Description: Controller class containing various routes for the website connected to HTML templates
 */
@Controller
@RequestMapping({"/", "/home"})
public class IndexController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    Api api;

    @Autowired
    private UserRepository userRepository;

    private boolean validateSession(HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        return user == null;
    }

    private boolean validateAdmin(HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        return user.getAdmin();
    }

    // home/login endpoint for logging in; first thing you see when going to the website
    @RequestMapping(value = "/")
    public String loginForm(Model model, HttpSession session) {
        // checking if a user is already stored in a session
        User user = (User) session.getAttribute("USER_SESSION");
        // redirect to their lists
        if (user != null) {
            Integer userId = user.getUserId();
            return "redirect:/lists?userId=" + userId;
        }
        // otherwise stay on the login page
        model.addAttribute("user", new User());
        return "home";
    }

    // login form post request
    @RequestMapping(value = "/loginForm", method = RequestMethod.POST)
    public String loginFormSubmit(@ModelAttribute User user,
                 HttpServletRequest request,
                 Model model,
                 @RequestParam String username,
                 @RequestParam String password) {

        User u = userRepository.findDistinctByUsernameLike(username);

        // checks if user has an account based on their username
        if (u != null) {
            // if user exists, ensure password is correct
            if(u.getPassword().equals(user.getPassword())) {
                // if correct, store user in session and redirect to their lists
                request.getSession().setAttribute("USER_SESSION", u);
                return "redirect:/lists?userId=" + u.getUserId();
            }
        }
        // otherwise refresh the page
        model.addAttribute("username", username);
        model.addAttribute("password", password);

        return "home";
    }

    // admin endpoint for admin only page
    @RequestMapping("/admin")
    public String allUsers(HttpSession session, Model model) {
        if (validateSession(session)) {
            return "redirect:/";
        }
        if (!validateAdmin(session)) {
            User user = (User) session.getAttribute("USER_SESSION");
            Integer userId = user.getUserId();

            return "redirect:/lists?userId=" + userId;
        }
        // admin page displays all registered users
        String uri = BASE_URI + "allUsers";
        RestTemplate restTemplate = new RestTemplate();

        User[] users = restTemplate.getForObject(uri, User[].class);
        User user = (User) session.getAttribute("USER_SESSION");
        Boolean admin = user.getAdmin();

        model.addAttribute("isAdmin", admin);
        model.addAttribute("users", users);

        return "adminPage";
    }

    // endpoint for logging out the user
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        if (validateSession(session)) {
            return "redirect:/";
        }
        // destroy user session, redirect to home/login form
        request.getSession().invalidate();
        return "redirect:/";
    }

    // endpoint for add user form, admin only
    @GetMapping(value="/admin/addUser")
    public String addUserForm(HttpSession session, Model model) {
        if (validateSession(session)) {
            return "redirect:/";
        }
        if (!validateAdmin(session)) {
            User user = (User) session.getAttribute("USER_SESSION");
            Integer userId = user.getUserId();

            return "redirect:/lists?userId=" + userId;
        }
        User user = (User) session.getAttribute("USER_SESSION");
        Boolean admin = user.getAdmin();

        model.addAttribute("isAdmin", admin);
        model.addAttribute("user", new User());
        return "addUserPage";
    }

    // post request for adding a new user account, admin only
    @PostMapping(value="/admin/addUser")
    public String addUserSubmit(@ModelAttribute User user, Model model) {
        User u = api.getUser(user.getUsername());

        if (u != null) {
            model.addAttribute("usernameTaken", true);
            return "addUserPage";
        }

        api.addUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());

        return "redirect:/admin";
    }

    // endpoint for register page
    @GetMapping(value="/register")
    public String registerUserForm(Model model) {
        model.addAttribute("user", new User());
        return "registerUserPage";
    }

    // register form post request
    @PostMapping(value="/register")
    public String registerUserSubmit(@ModelAttribute User user, Model model) {
        User u = api.getUser(user.getUsername());

        // check if username is already in use
        if (u != null) {
            model.addAttribute("usernameTaken", true);
            return "registerUserPage";
        }
        // otherwise save user to database, redirect to home/login page
        api.addUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());

        return "redirect:/";
    }

    // endpoint for adding an item to a wish list
    @GetMapping(value="/addItem")
    public String addItemForm(@RequestParam Integer listId, HttpSession session, Model model) {
        if (validateSession(session)) {
            return "redirect:/";
        }
        // adding empty item object and id values for thymeleaf to use when submitting post request form
        // userId and listId already initialized
        User user = (User) session.getAttribute("USER_SESSION");
        Integer userId = user.getUserId();
        Boolean admin = user.getAdmin();

        model.addAttribute("isAdmin", admin);
        model.addAttribute("item", new Item(userId, listId, "", "", "", ""));

        return "addItemPage";
    }

    // add item post request
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
        return "redirect:items?listId=" + item.getListId();
    }

    // endpoint for the user's profile page
    @GetMapping(value="/profile")
    public String profilePage(HttpSession session, Model model) {
        if (validateSession(session)) {
            return "redirect:/";
        }
        User user = (User) session.getAttribute("USER_SESSION");
        Boolean admin = user.getAdmin();

        model.addAttribute("isAdmin", admin);
        model.addAttribute("user", user);

        return "profilePage";
    }

    // endpoint for deleting the user's account
    @PostMapping(value="/deleteAccount")
    public String deleteAccount(HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        Integer userId = user.getUserId();

        // deletes the user, their lists, and their items from the database
        api.deleteAllItems(userId);
        api.deleteLists(userId);
        api.deleteUser(userId);

        // redirect to logout endpoint to destroy current session
        return "redirect:/logout";
    }
}
