package org.romafill.blogpostapp.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.romafill.blogpostapp.entity.Post;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        SessionFactory sessionFactory = applicationContext.getBean(SessionFactory.class);

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query<Post> postsQuery = session.createQuery("from Post", Post.class);
            List<Post> posts = postsQuery.list();


            for (Post post : posts) {
                System.out.println(post);
            }

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
