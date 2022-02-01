package com.qa.bank.exceptions;

import javax.persistence.EntityExistsException;

/**
 * Thrown when 'username' already exists
 * @author Vinay
 */
public class UsernameAlreadyExists extends EntityExistsException {
    /**
     * Default constructor for Exception
     */
    public UsernameAlreadyExists(){
        super();
    }

    /**
     * Constructor with message field for Exception
     * @param message Error message argument
     */
    public UsernameAlreadyExists(String message){
        super(message);
    }
}
