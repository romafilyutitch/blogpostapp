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
        return session.createQuery("from Post p left join fetch p.comments left join fetch p.author", Post.class);
    }

    @Override
    protected Query<Post> buildFindByIdQuery(Session session, long id) {
        Query<Post> postQuery = session.createQuery("from Post p left join fetch p.comments left join fetch p.author where p.id = :id", Post.class);
        postQuery.setParameter("id", id);

        return postQuery;
    }
}
