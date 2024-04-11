package com.bloomtech.socialfeed.models;

import java.time.LocalDateTime;

public class Post {
    private String username;
    private LocalDateTime postedon;
    private String body;

    /**
     * This is the constructor.
     */
    public Post() {
    }

    /**
     * Create an instance of post with arguments for all of his fields.
     * @param username is the username of the author of the post.
     * @param postedon is the DateTime of the post.
     * @param body is the body of the post.
     */
    public Post(String username, LocalDateTime postedon, String body) {
        this.username = username;
        this.postedon = postedon;
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getPostedon() {
        return postedon;
    }

    public void setPostedon(LocalDateTime postedon) {
        this.postedon = postedon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "username='" + username + '\'' +
                ", postedon='" + postedon + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
