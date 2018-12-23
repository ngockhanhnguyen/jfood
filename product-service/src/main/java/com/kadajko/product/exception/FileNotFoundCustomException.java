package com.kadajko.product.exception;

public class FileNotFoundCustomException extends RuntimeException {
    public FileNotFoundCustomException(String message) {
        super(message);
    }
    
    public FileNotFoundCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
