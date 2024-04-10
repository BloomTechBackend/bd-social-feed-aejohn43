package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.exceptions.EmailValidationException;

import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    /**
     * This is the constructor.
     */
    public EmailValidator() {
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\d\\w_\\.]+@\\w+\\.\\w+$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(email).matches();
    }

    @Override
    public void validate(Object emailData) {
        String email = (String) emailData;
        if (!isValidEmail(email)) {
            throw new EmailValidationException("Invalid Email: Email address must include '@' before domain and a " +
                    "domain identifier after a '.'!");
        }
    }
}
