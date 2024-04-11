package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.helpers.LocalDateTimeAdapter;
import com.bloomtech.socialfeed.models.Post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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
        Gson gson = builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

        Post[] response = gson.fromJson(content, Post[].class);

        if (response != null) {
            allPosts = new ArrayList<>(Arrays.asList(response));
        }
        return  allPosts;
    }

    /**
     *
     * @param username is the username of the user which post we are looking for.
     * @return a list of all posts.
     */
    public List<Post> findByUsername(String username) {
        return getAllPosts()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    /**
     * Add a post to th existing posts.
     * @param post is the post to be added.
     * @return all the existing posts.
     */
    public List<Post> addPost(Post post) {
        List<Post> allPosts = getAllPosts();
        allPosts.add(post);
        //TODO: Write the new Post data to the PostData.json file
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        String allposts = gson.toJson(allPosts);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(POST_DATA_PATH));
            writer.write(allposts);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //Return an updated list of all posts
        return getAllPosts();
    }
}
