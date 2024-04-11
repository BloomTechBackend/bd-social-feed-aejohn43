package com.bloomtech.socialfeed.validators;

public interface Validator {
    /**
     * This is an interface to be inherited by the classes of the validators package.
     * @param validationObject is the object to be validated.
     */
    void validate(Object validationObject);
}
