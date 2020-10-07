package com.assignment.AstrotalkAssignment.exception.handler;

import com.assignment.AstrotalkAssignment.DTO.ErrorResponseDTO;
import com.assignment.AstrotalkAssignment.exception.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Global controller for exception handling.
 * @author Karan
 */

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This is handleAllExceptions method.
     * @param ex ex
     * @return object
     */

 /*   @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        final List<String> details = new ArrayList<>();
        details.add(ex.toString());
        ErrorResponseDTO error = new ErrorResponseDTO("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    /**
     * This is handleInvalidDataException method.
     * @param ex ex
     * @return object
     */

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO("Invalid request", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
