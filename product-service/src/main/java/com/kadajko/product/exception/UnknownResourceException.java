package com.kadajko.product.exception;

public class UnknownResourceException extends RuntimeException {
    
    private String message;
    
    public UnknownResourceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
