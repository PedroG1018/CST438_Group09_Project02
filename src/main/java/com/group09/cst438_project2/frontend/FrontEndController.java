package com.group09.cst438_project2.frontend;

import com.group09.cst438_project2.backend.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class FrontEndController {
    public static final String BASE_URI = "localhost:8080/api/";

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    /*
    @RequestMapping("/allUsers")
    String allUsersModel(Model model) {
        String uri = BASE_URI + "allUsers";
        RestTemplate restTemplate = new RestTemplate();

        User[] users = restTemplate.getForObject(uri, User[].class);

        model.addAttribute("users", users);

        return "allUsers";
    }*/
}
