package com.qa.bank.exceptions;

import javax.persistence.EntityNotFoundException;

/**
 * Thrown when 'ID' is passed and user not found
 * @author Vinay
 */

public class UserNotFoundException extends EntityNotFoundException {

    /**
     * Constructor with message field for Exception
     * @param message Error message argument
     */
    public  UserNotFoundException(String message){
        super(message);
    }
}
