package org.romafill.blogpostapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romafill.blogpostapp.entity.Comment;
import org.romafill.blogpostapp.repository.ICommentRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "classpath:WEB-INF/applicationContext.xml")
class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private ICommentRepository repository;

    @Test
    public void giveTwoCommentsExists_whenFindAll_thenReturnTwoComments() {
        Comment comment1 = new Comment(1, "Test comment", null);
        Comment comment2 = new Comment(2, "Test comment", null);
        List<Comment> commentList = List.of(comment1, comment2);

        when(repository.findAll()).thenReturn(commentList);

        List<Comment> comments = commentService.findAll();

        assertNotNull(comments);
        assertEquals(2, comments.size());
    }

    @Test
    public void givenNoCommentsExist_whenFindAll_thenEmptyList() {
        List<Comment> commentList = List.of();
        when(repository.findAll()).thenReturn(commentList);

        List<Comment> comments = commentService.findAll();

        assertNotNull(comments);
        assertTrue(comments.isEmpty());
    }


    @Test
    public void givenCommentExists_whenFindById_thenReturnComment() {
        Comment comment = new Comment(1, "Test comment", null);

        when(repository.findById(1)).thenReturn(Optional.of(comment));

        Comment foundComment = commentService.findById(1);

        assertNotNull(foundComment);
        assertEquals(comment, foundComment);
    }

    @Test
    public void givenCommentNotExist_whenFindById_thenThrowException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> commentService.findById(1));
    }

    @Test
    public void givenCommentExists_whenUpdateComment_thenCommentUpdated() {
        Comment comment = new Comment(1, "Test comment", null);

        when(repository.update(comment)).thenReturn(comment);

        Comment updatedComment = commentService.update(comment);

        assertNotNull(updatedComment);
        assertEquals(comment, updatedComment);
    }

    @Test
    public void givenCommentExists_whenRemoveComment_thenCommentRemoved() {
        Comment comment = new Comment(1, "Test comment", null);

        when(repository.findById(1)).thenReturn(Optional.of(comment));

        Comment foundComment = commentService.findById(1);

        commentService.remove(foundComment);

        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> commentService.findById(1));
    }
}