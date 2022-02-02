package com.qa.bank.controller;

import com.qa.bank.data.entity.User;
import com.qa.bank.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * UserController layer integration test with UserService
 * @author Vinay
 */
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {
    @Autowired
    private UserController userController;

    // UserService will be mocked
    @MockBean
    private UserService userService;

    // Pre- populating data for testing
    private List<User> users = new ArrayList<>();
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
     * CREATE Operations
     * createUser
     */
    @Test
    public void createUserTest(){
        // Mocking UserService createUser method
        when(userService.createUser(dummyUser)).thenReturn(validUser);
        assertThat(ResponseEntity.status(HttpStatus.CREATED).body(validUser))
                .isEqualTo(userController.createUser(dummyUser));
        verify(userService).createUser(dummyUser);
    }

    /**
     * READ Operations
     * getUsers Test
     * getUserByUsername Test
     */
    @Test
    public void getUsersTest(){
        // Mocking UserService getUsers method
        when(userService.getUsers()).thenReturn(users);
        assertThat(ResponseEntity.status(HttpStatus.OK).body(users))
                .isEqualTo(userController.getUsers());
        verify(userService).getUsers();
    }

    @Test
    public void getUserByUsernameTest(){
        when(userService.getUserByUsername(dummyUser.getUsername())).thenReturn(validUser);
        assertThat(ResponseEntity.status(HttpStatus.FOUND).body(validUser))
                .isEqualTo(userController.getUserByUsername(dummyUser.getUsername()));
        verify(userService).getUserByUsername(dummyUser.getUsername());
    }

    /**
     * UPDATE Operations
     * updateUserTest
     */
    @Test
    public void updateUserTest(){
        // Mocking UserService updateUser method
        when(userService.updateUser(validUser.getId(), dummyUser)).thenReturn(validUser);
        assertThat(ResponseEntity.status(HttpStatus.ACCEPTED).body(validUser))
                .isEqualTo(userController.updateUser(validUser.getId(), dummyUser));
        verify(userService).updateUser(validUser.getId(), dummyUser);
    }

    /**
     * DELETE Operations
     * deleteUser Test
     */
    @Test
    public void deleteUser(){
        // Mocking UserService deleteUser method
        when(userService.deleteUser(validUser.getId())).thenReturn(dummyUser);
        assertThat(ResponseEntity.status(HttpStatus.ACCEPTED).body(dummyUser))
                .isEqualTo(userController.deleteUser(validUser.getId()));
        verify(userService).deleteUser(validUser.getId());
    }

}
