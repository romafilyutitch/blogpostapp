package org.romafill.blogpostapp.controller;

import org.romafill.blogpostapp.service.IPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    private final IPostService postService;

    public HelloController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        System.out.println("hello");
        model.addAttribute("message", "Hello, Thymeleaf");
        return "helloTemplate";
    }
}
