package org.romafill.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.romafill.entity.Post;

public class Main {
    public static void main(String[] args) {
        Configuration configure = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configure.buildSessionFactory();

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Post post = session.get(Post.class, 1);
            post.setText("Another updated text");

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
