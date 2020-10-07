package com.assignment.AstrotalkAssignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is custom exception class.
 * @author Karan
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConnectionAlreadyExistException extends RuntimeException {
    public ConnectionAlreadyExistException(String exception) {
        super(exception);
    }
}