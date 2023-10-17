package org.romafill.blogpostapp.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.romafill.blogpostapp.entity.Comment;
import org.romafill.blogpostapp.entity.Post;
import org.romafill.blogpostapp.entity.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configure = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configure.buildSessionFactory();

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query<User> userQuery = session.createQuery("from User ", User.class);
            List<User> users = userQuery.list();
            User user = null;
            if (users.isEmpty()) {
                user = new User();
                user.setUsername("Roma fill");
                session.persist(user);
            } else {
                user = users.get(0);
            }

            Post post = new Post();
            post.setTitle("Test post");
            post.setText("Test post to check the comments");
            post.setAuthor(user);

            Comment comment = new Comment();
            comment.setAuthor(user);
            comment.setText("Test comment for post");

            post.getComments().add(comment);

            session.persist(post);
            transaction.commit();

            Query<Comment> fromComment = session.createQuery("from Comment ", Comment.class);
            List<Comment> list = fromComment.list();
            for (Comment comment1 : list) {
                System.out.println(comment1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
