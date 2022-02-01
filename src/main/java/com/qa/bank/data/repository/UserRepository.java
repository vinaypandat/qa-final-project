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

}