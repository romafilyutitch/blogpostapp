package org.romafill.blogpostapp.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T> implements IRepository<T> {

    private final SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(AbstractRepository.class);

    public AbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<T> findAll() {
        logger.debug("Finding all entities");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query<T> resultQuery = buildListQuery(session);

            logger.debug("Query string built: {}", resultQuery.getQueryString());

            List<T> result = resultQuery.list();

            transaction.commit();

            logger.info("Entities are found");
            return result;
        } catch (Exception e) {
            logger.error("Exception occurred during finding all entities", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Optional<T> findById(long id) {
        logger.debug("Finding entity by id: {}", id);
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query<T> entityQuery = buildFindByIdQuery(session, id);

            logger.debug("Query string built: {}", entityQuery.getQueryString());

            T result = entityQuery.getSingleResultOrNull();

            if (result == null) {
                logger.info("Entity found by id: {}", id);
            } else {
                logger.info("Entity not found by id: {}", id);
            }

            transaction.commit();

            return Optional.ofNullable(result);
        } catch (Exception e) {
            logger.error("Exception occurred during finding entity by id", e);
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }

    @Override
    public T save(T entity) {
        logger.debug("Saving entity");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(entity);

            transaction.commit();

            logger.debug("Entity saved");

            return entity;
        } catch (Exception e) {
            logger.error("Exception occurred during saving entity", e);
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }

    @Override
    public T update(T entity) {
        logger.debug("Updating entity");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.merge(entity);

            transaction.commit();

            logger.debug("Entity updated");

            return entity;
        } catch (Exception e) {
            logger.error("Exception occurred during updating entity", e);
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }

    @Override
    public void remove(T entity) {
        logger.debug("Removing entity");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.remove(entity);

            transaction.commit();

            logger.debug("Entity removed");

        } catch (Exception e) {
            logger.error("Exception occurred during removing entity", e);
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }

    protected abstract Query<T> buildListQuery(Session session);

    protected abstract Query<T> buildFindByIdQuery(Session session, long id);
}
