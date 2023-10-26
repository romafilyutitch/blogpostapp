package org.romafill.blogpostapp.controller;

import org.romafill.blogpostapp.entity.Post;
import org.romafill.blogpostapp.service.IPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {

    private final IPostService postService;

    public HelloController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String posts(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "mainPage";
    }
}
