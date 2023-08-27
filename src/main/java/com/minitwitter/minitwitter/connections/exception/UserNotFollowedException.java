package com.minitwitter.minitwitter.connections.exception;

public class UserNotFollowedException extends RuntimeException{
    public UserNotFollowedException() {
        super();
    }

    public UserNotFollowedException(String message) {
        super(message);
    }
}
