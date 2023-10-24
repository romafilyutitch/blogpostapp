package org.romafill.blogpostapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romafill.blogpostapp.entity.User;
import org.romafill.blogpostapp.repository.IUserRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository repository;

    @Test
    public void givenTwoUsersExits_whenFindAll_thenReturnTwoUsers() {
        User user1 = new User(1, "User1", LocalDateTime.now(), Collections.emptySet());
        User user2 = new User(2, "User2", LocalDateTime.now(), Collections.emptySet());
        List<User> usersList = List.of(user1, user2);
        when(repository.findAll()).thenReturn(usersList);

        List<User> users = userService.findAll();

        assertNotNull(users);
        assertEquals(2, users.size());
    }


    @Test
    public void givenNoUsersExist_whenFindAll_thenEmptyList() {
        List<User> usersList = List.of();
        when(repository.findAll()).thenReturn(usersList);

        List<User> users = userService.findAll();

        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    public void givenUserExist_whenFindById_thenReturnUser() {
        User user = new User(1, "User1", LocalDateTime.now(), Collections.emptySet());
        when(repository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(1);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    public void givenUserNotExists_whenFindById_thenThrowException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findById(1));
    }

    @Test
    public void givenNewUser_whenUserSaved_thenReturnUserWithId() {
        User user = new User(1, "User1", LocalDateTime.now(), Collections.emptySet());

        when(repository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    public void givenUserExists_whenUpdateUser_thenUserUpdated() {
        User user = new User(1, "User1", LocalDateTime.now(), Collections.emptySet());

        when(repository.update(user)).thenReturn(user);

        User updatedUser = userService.update(user);

        assertNotNull(updatedUser);
        assertEquals(user, updatedUser);
    }

    @Test
    public void givenUserExists_whenRemoveUser_thenUserRemoved() {
        User user = new User(1, "User1", LocalDateTime.now(), Collections.emptySet());

        when(repository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(1);

        userService.remove(foundUser);

        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findById(1));
    }

}