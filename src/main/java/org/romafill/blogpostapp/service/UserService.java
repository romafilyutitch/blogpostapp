package org.romafill.blogpostapp.service;

import org.romafill.blogpostapp.entity.User;
import org.romafill.blogpostapp.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final IUserRepository repository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        logger.info("Finding all users");
        return repository.findAll();
    }

    @Override
    public User findById(long id) {
        logger.info("Finding user by ID: {}", id);
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    @Override
    public User save(User entity) {
        logger.info("Saving user: {}", entity);
        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        logger.info("Updating user: {}", entity);
        return repository.update(entity);
    }

    @Override
    public void remove(User entity) {
        logger.info("Removing user: {}", entity);
        repository.remove(entity);
    }
}
