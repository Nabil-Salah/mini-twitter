package com.minitwitter.minitwitter.connections.exception;

public class UserAlreadyFollowedException extends RuntimeException{
    public UserAlreadyFollowedException() {
        super();
    }

    public UserAlreadyFollowedException(String message) {
        super(message);
    }
}