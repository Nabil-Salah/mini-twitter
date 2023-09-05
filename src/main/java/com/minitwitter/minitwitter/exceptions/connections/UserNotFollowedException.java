package com.minitwitter.minitwitter.exceptions.connections;

public class UserNotFollowedException extends RuntimeException{
    public UserNotFollowedException() {
        super();
    }

    public UserNotFollowedException(String message) {
        super(message);
    }
}
