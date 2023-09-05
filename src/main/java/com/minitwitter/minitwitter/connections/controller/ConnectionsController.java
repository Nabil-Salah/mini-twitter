package com.minitwitter.minitwitter.connections.controller;

import com.minitwitter.minitwitter.connections.model.User;
import com.minitwitter.minitwitter.connections.service.ConnectionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/connections")
public class ConnectionsController {
    private final ConnectionsService connectionsService;

    public ConnectionsController(ConnectionsService connectionsService) {
        this.connectionsService = connectionsService;
    }

    @PostMapping("/{follower}/follow/{following}")
    public ResponseEntity<Object> followUser(@PathVariable String follower, @PathVariable String following) {
        connectionsService.followUser(follower,following);
        return new ResponseEntity<>("Followed successfully!", HttpStatus.OK);
    }


    @PostMapping("/{follower}/unfollow/{following}")
    public ResponseEntity<Object> unFollowUser(@PathVariable String follower, @PathVariable String following) {
        connectionsService.unFollowUser(follower,following);
        return new ResponseEntity<>("Unfollowed successfully!", HttpStatus.OK);
    }


    @GetMapping("/{username}/following")
    public ResponseEntity<Object> getUserFollowing(@PathVariable String username){
        return new ResponseEntity<>(connectionsService.getUserFollowing(username),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers(){
        return new ResponseEntity<>(connectionsService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<Object> getUserFollowers(@PathVariable String username){
        return new ResponseEntity<>(connectionsService.getUserFollowers(username),HttpStatus.OK);
    }

}

