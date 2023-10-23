package org.romafill.blogpostapp.service;

import org.romafill.blogpostapp.entity.User;
import org.romafill.blogpostapp.repository.IUserRepository;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(long id) {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException(User.class.getName(), id));
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        return repository.update(entity);
    }

    @Override
    public void remove(User entity) {
        repository.remove(entity);
    }
}
