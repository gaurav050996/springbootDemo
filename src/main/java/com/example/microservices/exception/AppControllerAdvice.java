package com.example.microservices.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@Component
public class AppControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstrainViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        return new ResponseEntity<>(new ErrorMessage(constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList()).get(0)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MicroServiceException.class)
    public ResponseEntity<?> handleNoRecordsException(MicroServiceException ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()),HttpStatus.NOT_FOUND);
    }
}
