package com.example.praktika.exceptionhandler;

import com.example.praktika.exceptionhandler.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleException(NotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, status);
    }
}