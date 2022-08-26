package com.mo1ty.medcenterapp.controller.exception;

public class InvalidValuesInputException extends RuntimeException{

    public InvalidValuesInputException() {
    }

    public InvalidValuesInputException(String message) {
        super(message);
    }

    public InvalidValuesInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValuesInputException(Throwable cause) {
        super(cause);
    }

    public InvalidValuesInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
