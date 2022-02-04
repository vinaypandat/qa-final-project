package com.qa.bank.service;

import com.qa.bank.data.entity.User;
import com.qa.bank.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing of each method of UserService layer.
 * @author Vinay
 */
@SpringBootTest
public class UserServiceUnitTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Pre- populating data for testing
    private final List<User> usersInDatabase = new ArrayList<>();
    private User dummyUser;
    private User validUser;
    Long nextUserId;

    /**
     * This will initialise data before each test runs
     */
    @BeforeEach
    public void init(){
        List<User> users = List.of(
                new User(1L,"neo1", "pass1", "Neo", "Chan", 23),
                new User(2L,"jackr", "pass2", "Jack", "Reacher", 25),
                new User(3L,"lilya", "pass3", "Lily", "Adams", 34)
        );
        usersInDatabase.addAll(userRepository.saveAll(users));
        int databaseSize = usersInDatabase.size();
        nextUserId = usersInDatabase.get(databaseSize - 1).getId() + 1;
        dummyUser = new User("nickf", "password", "Nick", "Fury", 31);
        validUser = new User(4L,"nickf", "password", "Nick", "Fury", 31);
    }

    /**
     * Deletes data before each test
     */
    @AfterEach
    public void deleteDataBeforeEachTest() {
        userRepository.deleteAll();
    }

    /**
     * CREATE Operations
     * createUser Test
     * need to resolve this
     */
    @Test
    public void createUserTest(){
        validUser.setId(nextUserId);
        assertThat(validUser).isEqualTo(userService.createUser(dummyUser));
    }

    /**
     * READ Operations
     * getUsers Test
     */
    @Test
    public void getUsersTest(){
        assertThat(usersInDatabase).isEqualTo(userService.getUsers());
    }

    /**
     * UPDATE Operations
     * updateUserTest
     */
    @Test
    public void updateUser(){
        validUser.setId(2L);
        assertThat(validUser).isEqualTo(userService.updateUser(2L, dummyUser));
    }

    /**
     * DELETE Operations
     * deleteUser Test
     */
    @Test
    public void deleteUser(){

        User actualUser = usersInDatabase.get(1);
        User expectedUser = new User(actualUser.getUsername(), actualUser.getPassword(),
                actualUser.getFirstName(), actualUser.getLastName(), actualUser.getAge());
        assertThat(expectedUser).isEqualTo(userService.deleteUser(actualUser.getId()));
    }

}
