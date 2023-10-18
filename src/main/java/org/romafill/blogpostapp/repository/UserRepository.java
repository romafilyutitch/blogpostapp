package org.romafill.blogpostapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.romafill.blogpostapp.entity.User;

import javax.naming.StringRefAddr;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Query<User> buildListQuery(Session session) {
        return session.createQuery("from User", User.class);
    }

    @Override
    protected String getEntityName() {
        return User.class.getName();
    }
}
