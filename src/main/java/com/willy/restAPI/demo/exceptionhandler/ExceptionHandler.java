package com.willy.restAPI.demo.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.willy.restAPI.demo.exceptionhandler.exception.BadInputException;
import com.willy.restAPI.demo.exceptionhandler.exception.ResultNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> handler(Throwable throwable) {
        return errorResponse(throwable, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> notFoundHandler(ResultNotFoundException ex) {
        return errorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> badInputHandler(BadInputException ex) {
        return errorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> jsonprocessHandler(JsonProcessingException ex) {
        return errorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // utils
    private ResponseEntity<ErrorMessage> errorResponse(Throwable throwable, HttpStatus status) {
        LOG.error("========> ERROR caught: " + throwable.getMessage(), throwable);
        return response(new ErrorMessage(throwable), status);
    }

    private <T> ResponseEntity<T> response(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }
}
