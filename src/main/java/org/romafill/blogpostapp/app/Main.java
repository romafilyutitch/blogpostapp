package org.romafill.blogpostapp.app;

import org.romafill.blogpostapp.entity.Post;
import org.romafill.blogpostapp.repository.IPostRepository;
import org.romafill.blogpostapp.service.EntityNotFoundException;
import org.romafill.blogpostapp.service.IPostService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        IPostService postRepository = applicationContext.getBean(IPostService.class);

        List<Post> posts = postRepository.findAll();

        for (Post post : posts) {
            System.out.println(post);
        }

        try {
            System.out.println("Find by id ");
            Post post = postRepository.findById(100);

            System.out.println(post);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
        }


    }
}
