package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.exceptions.UserValidationException;
import com.bloomtech.socialfeed.models.Role;
import com.bloomtech.socialfeed.models.User;

import java.util.regex.Pattern;

public class UserInfoValidator implements Validator {

    private boolean isValidUsername(String username) {
        String regex1 = "^....+$";
        String regex2 = "^[A-Z].+$";
        String regex3 = "^[\\d\\w]+$";
        Pattern p1 = Pattern.compile(regex1);
        Pattern p2 = Pattern.compile(regex2);
        Pattern p3 = Pattern.compile(regex3);
        return p1.matcher(username).matches() && p2.matcher(username).matches() && p3.matcher(username).matches() ;
    }

    private boolean isValidPassword(String password) {
        String regex = "^........+$";
        Pattern p1 = Pattern.compile(regex);
        regex = "^.*[A-Z].*$";
        Pattern p2 = Pattern.compile(regex);
        regex = "^.*[a-z].*$";
        Pattern p3 = Pattern.compile(regex);
        regex = "^.*\\d.*$";
        Pattern p4 = Pattern.compile(regex);
        regex = "^[\\w\\d!@#$%^&*]+$";
        Pattern p5 = Pattern.compile(regex);
        return p1.matcher(password).matches() && p2.matcher(password).matches() && p3.matcher(password).matches() &&
                p4.matcher(password).matches() && p5.matcher(password).matches();
    }

    @Override
    public void validate(Object userData) {

        User user = (User) userData;

        if (!isValidUsername(user.getUsername())) {
            throw new UserValidationException("Invalid Username: Username must be at least 4 characters long, " +
                    "must begin with an uppercase letter, and may not contain special characters or spaces!");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new UserValidationException("Invalid Password: Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, one lowercase letter, and one special character!");
        }
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
    }
}
