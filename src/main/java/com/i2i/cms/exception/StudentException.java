package com.i2i.cms.exception;

import java.lang.RuntimeException;

/**
 * <p>
 * This class deal with custom exception.
 * </p>
 */

public class StudentException extends RuntimeException {
    
    public StudentException(String message, Throwable t) {
        super(message, t);
    }
}