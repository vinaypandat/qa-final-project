package com.qa.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.bank.data.entity.User;
import com.qa.bank.data.repository.UserRepository;
import com.qa.bank.exceptions.ErrorModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * This is UserController system integration test
 * @author Vinay
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)     // For parallel testing
@AutoConfigureMockMvc
@Transactional
public class UserControllerSystemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private ErrorModel errorModel;

    // Pre-populating data for testing
    private final List<User> usersInDatabase = new ArrayList<>();
    private User dummyUser;
    private User validUser;
    private MockHttpServletRequestBuilder mockRequest;
    Long nextUserId;
    LocalDateTime dateTime;
    String usernameExistsMessage;
    String userNotFoundMessage;
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
        dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        usernameExistsMessage = "{\n" +
                "    \"httpStatus\": \"CONFLICT\",\n" +
                "    \"dateTime\": \"" + dateTime + "\",\n" +
                "    \"error\": \"User with this username already exists\"\n" +
                "}";
    }

    /**
     * CREATE
     * createUser valid and invalid operations tests
     */
    @Test
    public void createUserTest() throws Exception {
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/user/register/");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(dummyUser));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isCreated();
        ResultMatcher content = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(validUser));
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);

    }

    @Test
    public void createUserExceptionTest() throws Exception {
        User existingUser = new User("lilya", "pass3", "Lily", "Adams", 34);
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/user/register/");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(existingUser));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isConflict();
        ResultMatcher content = MockMvcResultMatchers.content().json(usernameExistsMessage);
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);

    }

    /**
     * READ
     * getUsers test
     */
    @Test
    public void getUsersTest() throws Exception {
        // This will mock the web request
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/user/");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isOk();
        ResultMatcher content = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(usersInDatabase));
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);
    }

    /**
     * UPDATE
     * updateUser valid and invalid operations test
     */
    @Test
    public void updateUserTest() throws Exception{
        User updatedUser = new User(3L,"nickf", "password", "Nick", "Fury", 31);
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/user/update/3");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(dummyUser));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
        ResultMatcher content = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedUser));
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);
    }

    @Test
    public void updateUserExceptionTest(){
        // To do
    }

    /**
     * DELETE
     * deleteUser valid and invalid operations test
     */
    @Test
    public void deleteUserTest() throws Exception{
        User deletedUser = new User("lilya", "pass3", "Lily", "Adams", 34);
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/user/delete/3");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
        ResultMatcher content = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(deletedUser));
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);
    }

    @Test
    public void deleteUserExceptionTest(){
        // To do
    }

    /**
     * Field Validation test
     */
    @Test
    public void fieldValidationExceptionTest(){
        // To do
    }

}
