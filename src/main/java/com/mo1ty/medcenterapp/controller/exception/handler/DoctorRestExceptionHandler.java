package com.mo1ty.medcenterapp.controller.exception.handler;

import com.mo1ty.medcenterapp.controller.error.response.DoctorErrorResponse;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import com.mo1ty.medcenterapp.controller.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoctorRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DoctorErrorResponse> handleException(DataNotFoundException exception){

        DoctorErrorResponse error = new DoctorErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DoctorErrorResponse> handleException(InvalidInputException exception){

        DoctorErrorResponse error = new DoctorErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<DoctorErrorResponse> handleException(DataNotPresentException exception){

        DoctorErrorResponse error = new DoctorErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DoctorErrorResponse> handleException(Exception exception){

        DoctorErrorResponse error = new DoctorErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        exception.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
