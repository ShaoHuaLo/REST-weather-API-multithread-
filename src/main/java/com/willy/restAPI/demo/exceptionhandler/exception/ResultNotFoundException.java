package com.willy.restAPI.demo.exceptionhandler.exception;

public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(String message) {
        super(message);
    }
}
