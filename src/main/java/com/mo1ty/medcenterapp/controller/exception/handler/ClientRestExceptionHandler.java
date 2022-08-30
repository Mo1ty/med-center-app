package com.mo1ty.medcenterapp.controller.exception.handler;

import com.mo1ty.medcenterapp.controller.error.response.ClientErrorResponse;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ClientRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handleException(DataNotFoundException exception){

        ClientErrorResponse error = new ClientErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ClientErrorResponse> handleException(DataNotPresentException exception){

        ClientErrorResponse error = new ClientErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handleException(Exception exception){

        ClientErrorResponse error = new ClientErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
