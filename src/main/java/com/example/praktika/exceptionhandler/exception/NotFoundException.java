package com.example.praktika.exceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }

}