package com.qa.bank.service;

import com.qa.bank.data.entity.User;
import com.qa.bank.data.repository.UserRepository;
import com.qa.bank.exceptions.UserNotFoundException;
import com.qa.bank.exceptions.UsernameAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * UserService integration test with UserRepository
 * @author Vinay
 */

@WebMvcTest(UserService.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    // Pre-populating data for testing
    private final List<User> users = new ArrayList<>();
    private User dummyUser;
    private User validUser;

    /**
     * This will initialise data before each test runs
     */
    @BeforeEach
    public void init(){
        users.addAll(List.of(
                new User(1L,"neo1", "pass1", "Neo", "Chan", 23),
                new User(2L,"jackr", "pass2", "Jack", "Reacher", 25),
                new User(3L,"lilya", "pass3", "Lily", "Adams", 34)
        ));

        dummyUser = new User("nickf", "password", "Nick", "Fury", 31);
        validUser = new User(1L,"nickf", "password", "Nick", "Fury", 31);
    }

    /**
     * CREATE
     * createUser valid and invalid operations tests
     */
    @Test
    public void createUserTest(){
        when(userRepository.save(dummyUser)).thenReturn(validUser);
        assertThat(userService.createUser(dummyUser)).isEqualTo(validUser);
        verify(userRepository).save(dummyUser);
    }

    @Test
    public void createUserExceptionTest(){
        String existingUsername = "nickf";
        when(userRepository.existsByUsername(existingUsername)).thenReturn(true);
        UsernameAlreadyExists error = assertThrows(UsernameAlreadyExists.class, () ->
                userService.createUser(dummyUser));
        assertThat(error.getMessage())
                .isEqualTo("User with this username already exists");
        verify(userRepository).existsByUsername(existingUsername);
    }

    /**
     * READ
     * getUsers test
     */
    @Test
    public void getUsersTest(){
        when(userRepository.findAll()).thenReturn(users);
        assertThat(userService.getUsers()).isEqualTo(users);
        verify(userRepository).findAll();
    }

    /**
     * UPDATE
     * updateUser valid and invalid operations test
     */
    @Test
    public void updateUserTest(){
        User updatedUser = new User(validUser.getId(), validUser.getUsername(), validUser.getPassword(),
                                    validUser.getFirstName(), validUser.getLastName(),
                                validUser.getAge());
        when(userRepository.findById(validUser.getId())).thenReturn(Optional.of(validUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        assertThat(userService.updateUser(validUser.getId(), updatedUser)).isEqualTo(updatedUser);
        verify(userRepository).findById(validUser.getId());
        verify(userRepository).save(updatedUser);
    }

    @Test
    public void updateUserExceptionTest(){
        when(userRepository.findById(validUser.getId())).thenReturn(Optional.empty());
        UserNotFoundException error = assertThrows(UserNotFoundException.class, () ->
                userService.updateUser(validUser.getId(), dummyUser));
        assertThat(error.getMessage())
                .isEqualTo("User with ID " + validUser.getId() + " doesn't exist");
        verify(userRepository).findById(validUser.getId());
    }

    /**
     * DELETE
     * deleteUser valid and invalid operations test
     */
    @Test
    public void deleteUserTest(){
        when(userRepository.findById(validUser.getId())).thenReturn(Optional.of(validUser));
        assertThat(userService.deleteUser(validUser.getId())).isEqualTo(dummyUser);
        verify(userRepository).findById(validUser.getId());
        verify(userRepository).deleteById(validUser.getId());
    }

    @Test
    public void deleteUserExceptionTest(){
        when(userRepository.findById(validUser.getId())).thenReturn(Optional.empty());
        UserNotFoundException error = assertThrows(UserNotFoundException.class, () ->
                userService.deleteUser(validUser.getId()));
        assertThat(error.getMessage())
                .isEqualTo("User with ID " + validUser.getId() + " doesn't exist");
        verify(userRepository).findById(validUser.getId());
    }
}
