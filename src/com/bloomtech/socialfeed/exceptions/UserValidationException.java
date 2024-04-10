package com.bloomtech.socialfeed.exceptions;

public class UserValidationException extends RuntimeException {
    /**
     *
     * @param message is sent as argument to the function.
     * Don't know what it is for know.
     */
    public UserValidationException(String message) {
        super(message);
    }
}
