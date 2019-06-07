package com.example.resztki.exception;

import org.h2.engine.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice (basePackages = "com.example.resztki")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = (UserExists.class))
    public ResponseEntity<Object> handleError (Exception ex, WebRequest webRequest){
        String bodyError = "This User Does not Exists";

        return handleExceptionInternal(ex, bodyError, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);


    }


}
