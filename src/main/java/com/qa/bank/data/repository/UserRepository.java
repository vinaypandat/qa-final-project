package com.qa.bank.data.repository;

import com.qa.bank.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository to provide JPA functionalities
 * @author Vinay
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks if User with the given username exists
     * @param username username to be checked
     * @return Returns true if username exists otherwise false
     */
    boolean existsByUsername(String username);

    /**
     * Finds User by username in the database
     * @param username username to find
     * @return Returns User if exists in the database
     */
    User findByUsername(String username);

}
