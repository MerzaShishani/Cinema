package com.merza.Cinema.exception;

public class InvalidDeleteException extends RuntimeException{

    private String message;

    public InvalidDeleteException(String message) {
        super(message);
    }
}
