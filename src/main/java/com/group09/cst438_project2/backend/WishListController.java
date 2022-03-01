package com.group09.cst438_project2.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WishListController {
    @GetMapping("/allLists")
    List<String> all(){
        List<String> temp = new ArrayList<>();
        temp.add("foo");
        temp.add("bar");
        temp.add("baz");
        return temp;
    }
}
