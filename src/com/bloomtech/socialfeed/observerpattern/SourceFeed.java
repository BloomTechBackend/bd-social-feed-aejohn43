package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class SourceFeed implements Source {
    private final PostRepository postRepository = new PostRepository();

    private List<Post> posts;
    private List<Observer> observers = new ArrayList<>();

    /**
     * This constructor instantiate the source feed by instantiate the posts array.
     */
    public SourceFeed() {
        this.posts = new ArrayList<>();
    }

    /**
     *Return all posts from the PostData.json file.
     */
    public void getAllPosts() {
        postRepository.getAllPosts();
    }

    /**
     * This method adds a post to the posts field and notify the observers.
     * @param user is the author of the post
     * @param body is the content of the post
     * @return is the post object return
     */
    public Post addPost(User user, String body) {
        Post post = new Post(user.getUsername(),
                LocalDateTime.now(),
                body);
        posts = postRepository.addPost(post);
        updateAll();
        return post;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void updateAll() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
