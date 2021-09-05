package com.willy.restAPI.demo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> handler(Throwable ex) {
        ErrorMessage error = ErrorMessage.builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .message(ex.getMessage())
                                .timeStamp(System.currentTimeMillis())
                                .build();


        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
