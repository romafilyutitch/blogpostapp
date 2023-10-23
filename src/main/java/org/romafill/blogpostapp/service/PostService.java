package org.romafill.blogpostapp.service;

import org.romafill.blogpostapp.entity.Post;
import org.romafill.blogpostapp.repository.IPostRepository;

import java.util.List;
import java.util.Optional;

public class PostService implements IPostService {

    private final IPostRepository repository;

    public PostService(IPostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Post findById(long id) {
        Optional<Post> optionalPost = repository.findById(id);
        return optionalPost.orElseThrow(() -> new EntityNotFoundException("Post not found with ID: " + id));
    }

    @Override
    public Post save(Post entity) {
        return repository.save(entity);
    }

    @Override
    public Post update(Post entity) {
        return repository.update(entity);
    }

    @Override
    public void remove(Post entity) {
        repository.remove(entity);
    }
}
