package com.ecse428.project.fitboi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping("/")
    public String mainPage(){
        return "Index page, use Android App instead";
    }
}
