package org.romafill.blogpostapp.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {

    List<T> findAll();

    Optional<T> findById(long id);

    T save(T entity);

    T update(T entity);

    void remove(T entity);


}
