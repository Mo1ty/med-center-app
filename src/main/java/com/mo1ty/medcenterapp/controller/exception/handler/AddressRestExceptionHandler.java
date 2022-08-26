package com.mo1ty.medcenterapp.controller.exception.handler;

import com.mo1ty.medcenterapp.controller.error.response.AddressErrorResponse;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AddressErrorResponse> handleException(DataNotFoundException exception){

        AddressErrorResponse error = new AddressErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AddressErrorResponse> handleException(Exception exception){

        AddressErrorResponse error = new AddressErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
