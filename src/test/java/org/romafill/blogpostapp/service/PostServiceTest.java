package org.romafill.blogpostapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romafill.blogpostapp.entity.Post;
import org.romafill.blogpostapp.repository.IPostRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "classpath:WEB-INF/applicationContext.xml")
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private IPostRepository repository;

    @Test
    public void givenTwoPostsExist_whenFindAll_thenReturnTwoPosts() {
        Post post1 = new Post(1, "Test title", "Test text", LocalDateTime.now(), 0, null, null);
        Post post2 = new Post(2, "Test title", "Test text", LocalDateTime.now(), 0, null, null);
        List<Post> postList = List.of(post1, post2);
        when(repository.findAll()).thenReturn(postList);

        List<Post> posts = postService.findAll();

        assertNotNull(posts);
        assertEquals(2, posts.size());
    }

    @Test
    public void givenNoPostsExist_whenFindAll_thenReturnEmptyList() {
        List<Post> postList = List.of();
        when(repository.findAll()).thenReturn(postList);

        List<Post> posts = postService.findAll();

        assertNotNull(posts);
        assertTrue(posts.isEmpty());
    }

    @Test
    public void givenPostExists_whenFindById_thenReturnPost() {
        Post post = new Post(1, "Test title", "Test text", LocalDateTime.now(), 0, null, null);
        when(repository.findById(1)).thenReturn(Optional.of(post));

        Post foundPost = postService.findById(1);

        assertNotNull(foundPost);
        assertEquals(post, foundPost);
    }

    @Test
    public void givenPostNotExists_whenFindById_thenThrowException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> postService.findById(1));
    }

    @Test
    public void givenNewPost_whenPostSaved_thenReturnPostWithId() {
        Post post = new Post(1, "Test title", "Test text", LocalDateTime.now(),0,null, null);

        when(repository.save(post)).thenReturn(post);

        Post savedPost = postService.save(post);

        assertNotNull(savedPost);
        assertEquals(post, savedPost);
    }

    @Test
    public void givenPostExists_whenUpdatePost_thenPostUpdated() {
        Post post = new Post(1, "Test title", "Test text", LocalDateTime.now(), 0, null, null);

        when(repository.update(post)).thenReturn(post);

        Post updatedPost = postService.update(post);

        assertNotNull(updatedPost);
        assertEquals(post, updatedPost);
    }

    @Test
    public void givenPostExists_whenRemovePost_thenPostRemoved() {
        Post post = new Post(1, "Test title", "Test text", LocalDateTime.now(), 0, null, null);

        when(repository.findById(1)).thenReturn(Optional.of(post));

        Post foundPost = postService.findById(1);

        postService.remove(foundPost);

        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> postService.findById(1));
    }
}