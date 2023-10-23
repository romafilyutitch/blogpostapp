package org.romafill.blogpostapp.service;

import org.romafill.blogpostapp.entity.Comment;
import org.romafill.blogpostapp.repository.ICommentRepository;

import java.util.List;
import java.util.Optional;

public class CommentService implements ICommentService {

    private final ICommentRepository repository;

    public CommentService(ICommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Comment findById(long id) {
        Optional<Comment> optionalComment = repository.findById(id);
        return optionalComment.orElseThrow(() -> new EntityNotFoundException(Comment.class.getName(), id));
    }

    @Override
    public Comment save(Comment entity) {
        return repository.save(entity);
    }

    @Override
    public Comment update(Comment entity) {
        return repository.update(entity);
    }

    @Override
    public void remove(Comment entity) {
        repository.remove(entity);
    }
}
