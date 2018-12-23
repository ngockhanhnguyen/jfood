package com.kadajko.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    
    @ExceptionHandler(UnknownResourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error resourceNotFound(UnknownResourceException e) {
        return new Error(404, e.getMessage());
    }
}
