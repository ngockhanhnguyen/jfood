package com.kadajko.product.exception;

public class ImageFormatNotFoundException extends RuntimeException {
    public ImageFormatNotFoundException(String message) {
        super(message);
    }
    
    public ImageFormatNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
