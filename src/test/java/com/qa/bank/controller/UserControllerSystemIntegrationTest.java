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

    // Pre-populating data for testing
    private final List<User> usersInDatabase = new ArrayList<>();
    private User dummyUser;
    private User validUser;
    private MockHttpServletRequestBuilder mockRequest;
    Long nextUserId;
    LocalDateTime dateTime;
    String usernameExistsMessage;
    String userNotFoundMessage;
    String userFieldValidationMessage;
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
                "    \"error\": \"User with this username already exists\"\n" +
                "}";
        userNotFoundMessage = "{\n" +
                "    \"httpStatus\": \"NOT_FOUND\",\n" +
                "    \"error\": \"User with ID 10 doesn't exist\"\n" +
                "}";
        userFieldValidationMessage = "{\n" +
                "    \"httpStatus\": \"BAD_REQUEST\",\n" +
                "    \"error\": \"[age: must be greater than or equal to 18] \"\n" +
                "}";
    }

    /**
     * CREATE
     * createUser valid and invalid operations tests
     */
    @Test
    public void createUserTest() throws Exception {
        // Checking for valid non-existing username
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
        // Checking for invalid existing username
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
        // Checking for valid ID
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/user/update/3");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(dummyUser));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
        ResultMatcher content = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedUser));
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);
    }

    @Test
    public void updateUserExceptionTest() throws Exception{
        // Checking for invalid ID
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/user/update/10");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(dummyUser));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isNotFound();
        ResultMatcher content = MockMvcResultMatchers.content().json(userNotFoundMessage);
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);
    }

    /**
     * DELETE
     * deleteUser valid and invalid operations test
     */
    @Test
    public void deleteUserTest() throws Exception{
        User deletedUser = new User("lilya", "pass3", "Lily", "Adams", 34);
        // Checking for valid input ID
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/user/delete/3");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isAccepted();
        ResultMatcher content = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(deletedUser));
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);
    }

    @Test
    public void deleteUserExceptionTest() throws Exception{
        // Checking for invalid input ID
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/user/delete/10");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isNotFound();
        ResultMatcher content = MockMvcResultMatchers.content().json(userNotFoundMessage);
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);
    }

    /**
     * Field Validation test
     */
    @Test
    public void fieldValidationExceptionTest() throws Exception{
        // Checking invalid field i.e. age which should be between 18 and 120
        User invalidUser = new User("jackr", "pass2", "Jack", "Reacher", 15);
        // Checking for valid ID
        mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/user/register/");
        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(invalidUser));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher status = MockMvcResultMatchers.status().isBadRequest();
        ResultMatcher content = MockMvcResultMatchers.content().json(userFieldValidationMessage);
        mockMvc.perform(mockRequest).andExpect(status).andExpect(content);


    }

}
