package com.minitwitter.minitwitter.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    //FeedTweetService feedTweetService;

    @GetMapping
    public ResponseEntity<Object> test(Principal principal){
        return new ResponseEntity<>("Hello " + principal.getName(),HttpStatus.OK);
    }

    // TODO : redirect to the user's url
    /*@GetMapping("/redirect")
    public ResponseEntity<Object> redirected(Principal principal){

    }*/

   /*
    @GetMapping("/{username}")
    public ResponseEntity<Object> userFeed(@PathVariable String username){
        //feedService.getTweetsByUsername
    }*/
}
