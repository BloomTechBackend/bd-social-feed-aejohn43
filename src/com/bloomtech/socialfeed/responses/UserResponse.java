package com.bloomtech.socialfeed.responses;

import com.bloomtech.socialfeed.models.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    private List<User> users;

    public UserResponse(List <User> users) {
        this.users = users;
    }
    public List<User> getUsers () {
        return this.users;
    }
}
