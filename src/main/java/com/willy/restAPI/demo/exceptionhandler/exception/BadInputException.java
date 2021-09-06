package com.willy.restAPI.demo.exceptionhandler.exception;

public class BadInputException extends RuntimeException{
    public BadInputException() {
        super("Empty input or Data format was wrong");
    }
}
