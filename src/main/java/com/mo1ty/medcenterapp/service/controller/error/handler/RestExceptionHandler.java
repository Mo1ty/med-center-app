package com.mo1ty.medcenterapp.service.controller.error.handler;

import com.mo1ty.medcenterapp.service.controller.error.exception.CustomException;
import com.mo1ty.medcenterapp.service.controller.error.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public <T extends CustomException> ResponseEntity<ErrorResponse> handleException(T exception){

        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception){

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        exception.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
