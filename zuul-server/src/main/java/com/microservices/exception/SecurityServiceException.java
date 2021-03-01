package com.microservices.exception;

import org.springframework.http.HttpStatus;

public class SecurityServiceException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public SecurityServiceException(String message, HttpStatus status) {
        this.message = message;
        this.httpStatus = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
