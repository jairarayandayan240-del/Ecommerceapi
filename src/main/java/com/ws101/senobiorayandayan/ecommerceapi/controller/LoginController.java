package com.ws101.senobiorayandayan.ecommerceapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";   // refers to templates/login.html
    }
}