package com.bloomtech.socialfeed.services;

import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.repositories.UserRepository;
import com.bloomtech.socialfeed.validators.EmailValidator;
import com.bloomtech.socialfeed.validators.UserInfoValidator;

public class UserService {
    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();
    private static final EmailValidator emailValidator = new EmailValidator();
    private UserRepository userRepository;

    /**
     * The constructor.
     * @param userRepository To be comment.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This is the get  UserByUsername method witch return the user found.
     * @param username is the to be found user's username.
     * @return the user found with the given username.
     */
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found with username: " + username));
        return user;
    }

    /**
     * This method is saving a new created user.
     * @param user is the user to be saved.
     */
    public void save(User user) {
        userInfoValidator.validate(user);
        emailValidator.validate(user.getEmail());
        userRepository.save(user);
    }
}
