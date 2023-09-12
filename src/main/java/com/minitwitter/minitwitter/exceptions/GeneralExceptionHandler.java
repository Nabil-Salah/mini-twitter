package com.minitwitter.minitwitter.exceptions;

import com.minitwitter.minitwitter.exceptions.connections.UserAddedBeforeException;
import com.minitwitter.minitwitter.exceptions.connections.UserAlreadyFollowedException;
import com.minitwitter.minitwitter.exceptions.connections.UserNotFollowedException;
import com.minitwitter.minitwitter.exceptions.registration.UsernameAlreadyTakenException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.util.*;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
        /*Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });*/
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String errorMessage = fieldError.getDefaultMessage();
            errors.add(errorMessage);
        });
        System.out.println(errors);
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        HashMap<String,String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            errors.put(error.getPropertyPath().toString(),error.getMessage());
        });
        System.out.println("CONSTRAINT" + ex.getConstraintViolations());

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handle(NoSuchElementException ex) {

        return new ResponseEntity<>("Could not find what you requested!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Object> handle(InternalAuthenticationServiceException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handle(ConnectException ex){
        return new ResponseEntity<>("Could not connect to db , please retry again later!",HttpStatus.OK);

    }
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<Object> handle(UsernameAlreadyTakenException ex) {

        return new ResponseEntity<>("Username already taken!",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyFollowedException.class)
    ResponseEntity<Object> handle(UserAlreadyFollowedException e){
        return new ResponseEntity<>("You already follow this user! ", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFollowedException.class)
    ResponseEntity<Object> handle(UserNotFollowedException e){
        return new ResponseEntity<>("You are not following this user! ", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handle(Exception e){
        //e.printStackTrace();
        return new ResponseEntity<>("Error found! ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
