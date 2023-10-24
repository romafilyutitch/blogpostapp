package org.romafill.blogpostapp.service;

import org.romafill.blogpostapp.entity.Comment;
import org.romafill.blogpostapp.repository.ICommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class CommentService implements ICommentService {

    private final ICommentRepository repository;
    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CommentService(ICommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> findAll() {
        logger.info("Finding all comments");
        return repository.findAll();
    }

    @Override
    public Comment findById(long id) {
        logger.info("Finding comment by ID: {}", id);
        Optional<Comment> optionalComment = repository.findById(id);
        return optionalComment.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    @Override
    public Comment save(Comment entity) {
        logger.info("Saving comment: {}", entity);
        return repository.save(entity);
    }

    @Override
    public Comment update(Comment entity) {
        logger.info("Updating comment: {}", entity);
        return repository.update(entity);
    }

    @Override
    public void remove(Comment entity) {
        logger.info("Removing comment: {}", entity);
        repository.remove(entity);
    }
}
