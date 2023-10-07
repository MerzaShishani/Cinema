package com.merza.Cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String elementNotFoundHandler(ElementNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicateRecordExceptionHandler(DuplicateRecordException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ShowIsFullException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String showIsFullExceptionHandler(ShowIsFullException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidDeleteException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidDeleteExceptionHandler(InvalidDeleteException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ShowAlreadyEndedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ShowAlreadyEndedExceptionHandler(ShowAlreadyEndedException ex) {
        return ex.getMessage();
    }


}
