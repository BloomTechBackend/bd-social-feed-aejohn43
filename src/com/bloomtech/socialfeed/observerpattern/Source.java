package com.bloomtech.socialfeed.observerpattern;

public interface Source {
    /**
     *
     * @param observer is the observer of the Subject to be attached.
     */
    void attach(Observer observer);

    /**
     *
     * @param observer is the observer of the Subject to be detached.
     */
    void detach(Observer observer);

    /**
     * This update the feed of all the observers.
     */
    void updateAll();
}
