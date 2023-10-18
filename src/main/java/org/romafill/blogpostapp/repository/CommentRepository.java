package org.romafill.blogpostapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.romafill.blogpostapp.entity.Comment;

public class CommentRepository extends AbstractRepository<Comment> implements ICommentRepository {

    public CommentRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Query<Comment> buildListQuery(Session session) {
        return session.createQuery("from Comment", Comment.class);
    }

    @Override
    protected String getEntityName() {
        return Comment.class.getName();
    }
}
