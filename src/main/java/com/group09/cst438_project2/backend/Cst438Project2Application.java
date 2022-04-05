package com.group09.cst438_project2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * Class: CST438Project2Application.java
 * Last Modified: 03/23/2022
 * Description: Main class needed to run the application
 */
@Controller
@SpringBootApplication
public class Cst438Project2Application {
    public static void main(String[] args) {
        SpringApplication.run(Cst438Project2Application.class, args);
    }

}
