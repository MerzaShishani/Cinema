package com.merza.Cinema.exception;

public class ElementNotFoundException extends RuntimeException {
    private Long id;
    private String message;

    public ElementNotFoundException(Long id) {
        super("Could not find an element with id : " + id);
    }
    public ElementNotFoundException(String message) {
        super(message);
    }
}
