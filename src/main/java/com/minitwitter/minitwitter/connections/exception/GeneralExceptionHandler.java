package com.minitwitter.minitwitter.connections.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(UserAlreadyFollowedException.class)
    ResponseEntity<Object> handle(UserAlreadyFollowedException e){
        return new ResponseEntity<>("You already follow this user! ", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFollowedException.class)
    ResponseEntity<Object> handle(UserNotFollowedException e){
        return new ResponseEntity<>("You are not following this user! ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<Object> handle(NoSuchElementException e){
        return new ResponseEntity<>("Could not find user! ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handle(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>("Error found ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
