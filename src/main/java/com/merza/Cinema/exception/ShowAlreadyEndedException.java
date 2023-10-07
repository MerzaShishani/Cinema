package com.merza.Cinema.exception;

public class ShowAlreadyEndedException extends RuntimeException{

    public ShowAlreadyEndedException() {
        super("Show Already Ended");
    }
}
