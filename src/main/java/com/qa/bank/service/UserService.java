package com.qa.bank.service;

import com.qa.bank.data.entity.User;
import com.qa.bank.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * UserService to communicate with UserRepository
 * This contains business logic to perform CRUD operation on User entity
 * @author Vinay
 */

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * CREATE/register a User
     * @param user User to be registered
     * @return Returns registered user
     */
    public User createUser(User user){
        if (!userRepository.existsByUsername(user.getUsername())){
            return userRepository.save(user);
        } else{
            throw new EntityExistsException("User with this username already exists");
        }


    }
    /**
     * READ all users from database
     * @return Returns list of Users
     */
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * UPDATE user in database
     * @param id ID of user to update
     * @param user New details of User
     * @return Return changed details of User
     */
    public User updateUser(Long id, User user){
        User userToUpdate = userRepository.findById(id).orElseThrow(()-> {
            throw new EntityNotFoundException("Used with this ID doesn't exist");
        });
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setAge(user.getAge());
        return userRepository.save(userToUpdate);
    }

    /**
     * DELETE User in database
     * @param id ID of the user to be deleted
     * @return Returns deleted user
     */
    public User deleteUser(Long id){
        User deletedUser = userRepository.findById(id).orElseThrow(()-> {
            throw new EntityNotFoundException("Used with this ID doesn't exist");
        });
        userRepository.deleteById(id);
        return new User(
                deletedUser.getUsername(),
                deletedUser.getPassword(),
                deletedUser.getFirstName(),
                deletedUser.getLastName(),
                deletedUser.getAge()
        );
    }
}
