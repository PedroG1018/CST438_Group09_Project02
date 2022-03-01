package com.group09.cst438_project2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Cst438Project2Application {

/*
    @RequestMapping("/")
    @ResponseBody
    String home(@RequestParam(defaultValue = "test") String id) {

        if(id.equals("21233")){
            return "AHHHHHHHHHH yeah";
        }
        return "Hello world! param == " + id;
    }
*/
    @RequestMapping(value = "/name")
    @ResponseBody
    String name(){
        return "name";
    }

    public static void main(String[] args) {
        SpringApplication.run(Cst438Project2Application.class, args);
    }

}
