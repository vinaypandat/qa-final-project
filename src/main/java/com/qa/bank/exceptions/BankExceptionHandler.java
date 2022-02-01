package com.qa.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Handles the custom exception for this app
 * @author Vinay
 */
@ControllerAdvice
public class BankExceptionHandler {
    /**
     * Handles user not found exception
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorModel> userNotFound(UserNotFoundException userNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorModel(HttpStatus.NOT_FOUND, userNotFoundException.getMessage()));
    }

    /**
     * Handles username already exists exception
     */
    @ExceptionHandler(value = {UsernameAlreadyExists.class})
    public ResponseEntity<ErrorModel> userNameAlreadyExists(UsernameAlreadyExists usernameAlreadyExists){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorModel(HttpStatus.CONFLICT, usernameAlreadyExists.getMessage()));
    }

    /**
     * Handles data validation error exception
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorModel> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException){
        // Saved List of validation errors including fields
        List<FieldError> validationError = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        StringBuilder error = new StringBuilder();
        System.out.println(validationError.size());
        // Gets the field and reason for validation error from List
        for (FieldError x: validationError){
            error.append("[").append(x.getField()).append(": ").append(x.getDefaultMessage()).append("] ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorModel(HttpStatus.BAD_REQUEST, String.valueOf(error)));
    }

}
