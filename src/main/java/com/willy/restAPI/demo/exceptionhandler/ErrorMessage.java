package com.willy.restAPI.demo.exceptionhandler;

import lombok.Data;

@Data
public class ErrorMessage {
    private final String message;
    private final long timeStamp;
    private final String type;

    public ErrorMessage(Throwable ex) {
        this.message = ex.getMessage();
        this.timeStamp = System.currentTimeMillis();
        this.type = ex.getClass().getSimpleName();
    }
}
