package com.atg.harrykart.exception;

/**
 * CustomException to be thrown for InValidRequest
 * @author Parasuram
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
