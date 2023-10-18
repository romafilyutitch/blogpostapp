package org.romafill.blogpostapp.app;

import org.romafill.blogpostapp.entity.Post;
import org.romafill.blogpostapp.repository.IPostRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        IPostRepository postRepository = applicationContext.getBean(IPostRepository.class);

        List<Post> posts = postRepository.findAll();

        for (Post post : posts) {
            System.out.println(post);
        }
    }
}
