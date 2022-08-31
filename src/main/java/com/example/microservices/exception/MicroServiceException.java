package com.example.microservices.exception;

public class MicroServiceException extends RuntimeException {

    public MicroServiceException(String message){
        super(message);
    }
}
