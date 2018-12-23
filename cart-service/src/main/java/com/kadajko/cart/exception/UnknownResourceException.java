package com.kadajko.cart.exception;

public class UnknownResourceException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UnknownResourceException(String message) {
        super(message);
    }

    public UnknownResourceException(String message, Throwable e) {
        super(message, e);
    }
}
