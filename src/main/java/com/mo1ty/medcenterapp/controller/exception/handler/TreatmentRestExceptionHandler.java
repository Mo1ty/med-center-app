package com.mo1ty.medcenterapp.controller.exception.handler;

import com.mo1ty.medcenterapp.controller.error.response.TreatmentErrorResponse;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TreatmentRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TreatmentErrorResponse> handleException(DataNotFoundException exception){

        TreatmentErrorResponse error = new TreatmentErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<TreatmentErrorResponse> handleException(DataNotPresentException exception){

        TreatmentErrorResponse error = new TreatmentErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TreatmentErrorResponse> handleException(Exception exception){

        TreatmentErrorResponse error = new TreatmentErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
