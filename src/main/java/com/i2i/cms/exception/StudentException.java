package com.i2i.cms.exception;

import java.lang.RuntimeException;

/*
 * This class deal with custom exception
 */

public class StudentException extends RuntimeException {
    
    public StudentException(String message, Throwable t) {
        super(message, t);
    }
}