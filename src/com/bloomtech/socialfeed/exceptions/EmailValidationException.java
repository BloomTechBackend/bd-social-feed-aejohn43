package com.bloomtech.socialfeed.exceptions;

public class EmailValidationException extends RuntimeException {
    /**
     *
     * @param message is sent to the function. Don't know what it is for know.
     */
    public EmailValidationException(String message) {
        super(message);
    }
}
