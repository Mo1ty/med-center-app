package com.mo1ty.medcenterapp.controller.exception;

public class DataNotPresentException extends RuntimeException{

    public DataNotPresentException(String message) {
        super(message);
    }

    public DataNotPresentException() {
    }

    public DataNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotPresentException(Throwable cause) {
        super(cause);
    }

    protected DataNotPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
