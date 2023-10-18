package org.romafill.blogpostapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.romafill.blogpostapp.entity.Comment;
import org.romafill.blogpostapp.entity.User;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Query<User> buildListQuery(Session session) {
        return session.createQuery("from User", User.class);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
