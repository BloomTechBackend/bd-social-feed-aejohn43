package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;

import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class OUserFeed implements Observer {
    private User user;
    private List<Post> feed = new ArrayList<>();
    private SourceFeed sourceFeed = App.sourceFeed;

    /**
     *
     * @param user is the user pass to constructor.
     */
    public OUserFeed(User user) {
        this.user = user;
        this.sourceFeed.attach(this);
        this.update();
    }

    public User getUser() {
        return user;
    }

    public List<Post> getFeed() {
        return feed;
    }


    @Override
    public void update() {
        for (Post post : sourceFeed.getPosts()) {
            for (String username : this.user.getFollowing()) {
                if (username.equals(post.getUsername())) {
                    this.feed.add(post);
                }
            }
        }
    }
}
