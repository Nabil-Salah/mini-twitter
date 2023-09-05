package com.minitwitter.minitwitter.exceptions.connections;

public class UserAlreadyFollowedException extends RuntimeException{
    public UserAlreadyFollowedException() {
        super();
    }

    public UserAlreadyFollowedException(String message) {
        super(message);
    }
}
