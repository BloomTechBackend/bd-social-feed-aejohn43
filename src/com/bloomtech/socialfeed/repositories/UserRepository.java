package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.responses.UserResponse;
import com.bloomtech.socialfeed.validators.UserInfoValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final String USER_DATA_PATH = "src/resources/UserData.json";

    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();

    public UserRepository() {
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        //TODO: return parsed list of Users from UserData.json
        String line;
        String content = "";
        try {
            FileReader reader = new FileReader(USER_DATA_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
               content += line;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        User[] response = gson.fromJson(content, User[].class);

        if (response != null) {
            allUsers = new ArrayList<>(Arrays.asList(response));
        }
        return allUsers;
    }

    /**
     *
     * @param username is the user's to be find's username.
     * @return the User object found;
     */

    public Optional<User> findByUsername(String username) {
        return getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    /**
     *
     * @param user is the user to be saved.
     */

    public void save(User user) {
        List<User> allUsers = getAllUsers();

        Optional<User> existingUser = allUsers.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();

        if (!existingUser.isEmpty()) {
            throw new RuntimeException("User with name: " + user.getUsername() + " already exists!");
        }
        allUsers.add(user);
        //TODO: Write allUsers to UserData.json

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String allusers = gson.toJson(allUsers);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(App.USER_FILE_PATH));
            writer.write(allusers);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
