package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepository {
    private static final String POST_DATA_PATH = "src/resources/PostData.json";

    /**
     * This is the constructor.
     */
    public PostRepository() {
    }

    /**
     *
     * @return To be comment later.
     */
    public List<Post> getAllPosts() {
        List<Post> allPosts = new ArrayList<>();
        //TODO: return all posts from the PostData.json file
        String line;
        String content = "";
        try {
            FileReader reader = new FileReader(POST_DATA_PATH);
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

        Post[] response = gson.fromJson(content, Post[].class);

        if (response != null) {
            allPosts = new ArrayList<>(Arrays.asList(response));
        }
        return  allPosts;
    }

    /**
     *
     * @param username is the username of the user which post we are looking for.
     * @return
     */
    public List<Post> findByUsername(String username) {
        return getAllPosts()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Post> addPost(Post post) {
        List<Post> allPosts = new ArrayList<>();
        allPosts.add(post);

        //TODO: Write the new Post data to the PostData.json file
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String allposts = gson.toJson(allPosts);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(POST_DATA_PATH));
            writer.write(allposts);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //TODO: Return an updated list of all posts
        return getAllPosts();
    }
}
