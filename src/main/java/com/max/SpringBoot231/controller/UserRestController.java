package com.max.SpringBoot231.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class UserRestController {

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }
    @GetMapping("/user")
    public String user() {
        return"/userRest";
    }
}
