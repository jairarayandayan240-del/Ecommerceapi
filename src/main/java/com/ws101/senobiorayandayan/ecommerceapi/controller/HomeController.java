package com.ws101.senobiorayandayan.ecommerceapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Simple controller to handle root (/) requests.
 * Redirects to the products API so the app doesn't show a Whitelabel 404 page.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/api/v1/products";
    }
}
