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

    private final UserService userService;

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
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    /**
     * READ
     * @return List of users
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    /**
     * UPDATE
     * @param id ID of user to update
     * @param user New details of User
     * @return Return changed details of User
     */
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(id, user));
    }

    /**
     * DELETE
     * @param id ID of the user to be deleted
     * @return Returns deleted user
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.deleteUser(id));
    }
}
