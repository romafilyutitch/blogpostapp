package org.romafill.blogpostapp.repository;

import org.romafill.blogpostapp.entity.Post;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {

    List<Post> findAll();
    Optional<Post> findById(long id);
    Post save(Post newPost);
    Post update(Post post);
    void delete(Post post);
}
