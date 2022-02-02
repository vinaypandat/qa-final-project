package com.qa.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Error model to get the ResponseEntity in desired format
 * @author Vinay
 */

public class ErrorModel {
    private HttpStatus httpStatus;
    private LocalDateTime dateTime;
    private String error;

    public ErrorModel(HttpStatus httpStatus, String error) {
        this.httpStatus = httpStatus;
        this.dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.error = error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getError() {
        return error;
    }
}
