package com.microservice.productservices.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProjectException extends RuntimeException{
    private final HttpStatus httpStatus;

    public ProjectException(HttpStatus httpStatus,String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
