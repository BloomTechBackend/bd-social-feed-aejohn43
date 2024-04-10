package com.bloomtech.socialfeed;

import com.bloomtech.socialfeed.observerpattern.SourceFeed;

public class App {
    public static final SourceFeed sourceFeed = new SourceFeed();

    public static final String USER_FILE_PATH = "src/resources/UserData.json";
    public static final String POST_FILE_PATH = "src/resources/PostData.json";

    /**
     * This is the main method.
     * @param args is the arguments pass to the main method.
     */
    public static void main(String[] args) {
        SeedData.run();
    }
}
