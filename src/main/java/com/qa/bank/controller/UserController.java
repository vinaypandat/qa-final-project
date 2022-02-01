package com.qa.bank.controller;

import com.qa.bank.data.entity.User;
import com.qa.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController layer to communicate with UserService
 * @author Vinay
 */

@RestController
@RequestMapping(path = "/user")     // Path to access UserController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * CREATE
     * @param user User to be registered
     * @return Returns registered user
     */
    @PostMapping(path = "/register")
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    /**
     * READ
     * @return List of users
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
}
