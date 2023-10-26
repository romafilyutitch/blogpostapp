package org.romafill.blogpostapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/hello")
    public String hello(Model model) {
        System.out.println("hello");
        model.addAttribute("message", "Hello, Thymeleaf");
        return "helloTemplate";
    }
}
