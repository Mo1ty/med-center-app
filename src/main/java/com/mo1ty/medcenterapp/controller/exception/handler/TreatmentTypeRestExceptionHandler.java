package com.mo1ty.medcenterapp.controller.exception.handler;

import com.mo1ty.medcenterapp.controller.error.response.TreatmentTypeErrorResponse;
import com.mo1ty.medcenterapp.controller.exception.DataNotFoundException;
import com.mo1ty.medcenterapp.controller.exception.DataNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class TreatmentTypeRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TreatmentTypeErrorResponse> handleException(DataNotFoundException exception){

        TreatmentTypeErrorResponse error = new TreatmentTypeErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<TreatmentTypeErrorResponse> handleException(DataNotPresentException exception){

        TreatmentTypeErrorResponse error = new TreatmentTypeErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TreatmentTypeErrorResponse> handleException(Exception exception){

        TreatmentTypeErrorResponse error = new TreatmentTypeErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
