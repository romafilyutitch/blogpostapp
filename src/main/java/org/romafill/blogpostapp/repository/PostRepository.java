package org.romafill.blogpostapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.romafill.blogpostapp.entity.Post;

public class PostRepository extends AbstractRepository<Post> implements IPostRepository {

    public PostRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Query<Post> buildListQuery(Session session) {
        return session.createQuery("from Post", Post.class);
    }

    @Override
    protected String getEntityName() {
        return Post.class.getName();
    }
}
