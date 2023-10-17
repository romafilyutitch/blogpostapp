package org.romafill.blogpostapp.repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.romafill.blogpostapp.entity.Post;

import java.util.List;
import java.util.Optional;

public class PostRepository implements IPostRepository {

    private SessionFactory sessionFactory;

    public PostRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Post> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Query<Post> postsQuery = session.createQuery("from Post", Post.class);
            List<Post> posts = postsQuery.list();
            for (Post post : posts) {
                Hibernate.initialize(post.getComments());
            }

            transaction.commit();
            return posts;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Optional<Post> findById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Post post = session.get(Post.class, id);

            Hibernate.initialize(post.getComments());

            transaction.commit();

            return Optional.ofNullable(post);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Post save(Post newPost) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(newPost);
            Hibernate.initialize(newPost.getComments());

            transaction.commit();

            return newPost;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Post update(Post post) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.merge(post);
            Hibernate.initialize(post);

            transaction.commit();

            return post;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Post post) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.remove(post);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
