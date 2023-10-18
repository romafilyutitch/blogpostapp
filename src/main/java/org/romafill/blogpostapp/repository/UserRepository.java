package org.romafill.blogpostapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    protected Query<User> buildFindByIdQuery(Session session, long id) {
        Query<User> userQuery = session.createQuery("from User u where u.id = :id", User.class);
        userQuery.setParameter("id", id);

        return userQuery;
    }
}
