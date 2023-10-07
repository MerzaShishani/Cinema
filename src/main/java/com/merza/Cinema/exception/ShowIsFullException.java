package com.merza.Cinema.exception;

public class ShowIsFullException extends RuntimeException {

    private Long id;

    public ShowIsFullException(Long id) {
        super("Show with id : " + id +" is full, please choose another show");
    }
}
