package com.mo1ty.medcenterapp.controller.exception;

public class DataAlreadyExistsException extends RuntimeException{

    public DataAlreadyExistsException() {
    }

    public DataAlreadyExistsException(String message) {
        super(message);
    }

    public DataAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public DataAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
