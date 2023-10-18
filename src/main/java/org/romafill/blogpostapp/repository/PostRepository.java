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
        return session.createQuery("from Post p left join fetch p.comments", Post.class);
    }

    @Override
    protected Class<Post> getEntityClass() {
        return Post.class;
    }
}
