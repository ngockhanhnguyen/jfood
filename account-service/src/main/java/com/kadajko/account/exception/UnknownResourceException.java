package com.kadajko.account.exception;

public class UnknownResourceException extends RuntimeException {
    public UnknownResourceException(String message) {
        super(message);
    }

    public UnknownResourceException(String message, Throwable e) {
        super(message, e);
    }
}
