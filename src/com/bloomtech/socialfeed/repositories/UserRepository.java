package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.validators.UserInfoValidator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final String USER_DATA_PATH = "src/resources/UserData.json";

    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();

    /**
     * Is the default constructor but is not used.
     */
    public UserRepository() {
    }

    /**
     * Get all the users stocked in the UserData.json.
     * @return all the Users in the UserRepository.
     */
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
