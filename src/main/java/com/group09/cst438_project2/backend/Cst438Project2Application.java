package com.group09.cst438_project2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Cst438Project2Application {
    public static void main(String[] args) {
        SpringApplication.run(Cst438Project2Application.class, args);
    }
}
