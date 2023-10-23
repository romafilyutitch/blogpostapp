package org.romafill.blogpostapp.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T findById(long id);
    T save(T entity);
    T update(T entity);
    void remove(T entity);
}
