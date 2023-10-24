package org.romafill.blogpostapp.service;

import org.romafill.blogpostapp.entity.Post;
import org.romafill.blogpostapp.repository.IPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class PostService implements IPostService {

    private final IPostRepository repository;

    private final Logger logger = LoggerFactory.getLogger(PostService.class);

    public PostService(IPostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> findAll() {
        logger.info("Finding all posts");
        return repository.findAll();
    }

    @Override
    public Post findById(long id) {
        logger.info("Finding post by ID: {}", id);
        Optional<Post> optionalPost = repository.findById(id);
        return optionalPost.orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + id));
    }

    @Override
    public Post save(Post entity) {
        logger.info("Saving post: {}", entity);
        return repository.save(entity);
    }

    @Override
    public Post update(Post entity) {
        logger.info("Updating post: {}", entity);
        return repository.update(entity);
    }

    @Override
    public void remove(Post entity) {
        logger.info("Removing post: {}", entity);
        repository.remove(entity);
    }
}
