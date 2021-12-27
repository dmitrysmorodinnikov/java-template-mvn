package com.company.exceptions;

public class MyAppException extends RuntimeException {
    public MyAppException(String message, Exception e) {
        super(message, e);
    }
}
