package com.kadajko.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    
    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error emailExisted(EmailExistsException e) {
        return new Error(400, e.getMessage());
    }
    
    @ExceptionHandler(UnknownResourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error emailNotExists(UnknownResourceException e) {
        return new Error(400, e.getMessage());
    }
}
