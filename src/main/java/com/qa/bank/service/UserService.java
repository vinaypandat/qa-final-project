package com.qa.bank.service;

import com.qa.bank.data.entity.User;
import com.qa.bank.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService to communicate with UserRepository
 * @author Vinay
 */

@Service
public class UserService {
    private UserRepository userRepository;

    /**
     * Dependency injection(Constructor injection)
     * @param userRepository
     */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * CREATE/register a User
     * @param user User to be registered
     * @return Returns registered user
     */
    public User create(User user){
        return userRepository.save(user);

    }
    /**
     * READ all users from database
     * @return Returns list of Users
     */
    public List<User> getUsers(){
        return userRepository.findAll();
    }


}
