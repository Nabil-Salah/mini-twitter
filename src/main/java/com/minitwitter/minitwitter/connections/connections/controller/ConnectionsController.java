package com.example.twitterconnections.controller;

import com.example.twitterconnections.service.ConnectionsService;
import com.example.twitterconnections.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class ConnectionsController {
    private final ConnectionsService connectionsService;

    public ConnectionsController(ConnectionsService connectionsService) {
        this.connectionsService = connectionsService;
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user){
        connectionsService.addUser(user);
        return new ResponseEntity<>("Created user successfully!",HttpStatus.OK);
    }

    @PostMapping("/id/{followerId}/follow/{followingId}")
    public ResponseEntity<Object> followUser(@PathVariable UUID followerId, @PathVariable UUID followingId) {
        connectionsService.followUser(followerId,followingId);
        return new ResponseEntity<>("Followed successfully!", HttpStatus.OK);
    }
    @PostMapping("/username/{follower}/follow/{following}")
    public ResponseEntity<Object> followUser(@PathVariable String follower, @PathVariable String following) {
        connectionsService.followUser(follower,following);
        return new ResponseEntity<>("Followed successfully!", HttpStatus.OK);
    }

    @PostMapping("/id/{followerId}/unfollow/{followingId}")
    public ResponseEntity<Object> unFollowUser(@PathVariable UUID followerId, @PathVariable UUID followingId) {
        connectionsService.unFollowUser(followerId,followingId);
        return new ResponseEntity<>("Unfollowed successfully!", HttpStatus.OK);
    }

    @PostMapping("/username/{follower}/unfollow/{following}")
    public ResponseEntity<Object> unFollowUser(@PathVariable String follower, @PathVariable String following) {
        connectionsService.unFollowUser(follower,following);
        return new ResponseEntity<>("Unfollowed successfully!", HttpStatus.OK);
    }


    @GetMapping("/id/{userId}/following")
    public ResponseEntity<Object> getUserFollowing(@PathVariable UUID userId){
        return new ResponseEntity<>(connectionsService.getUserFollowing(userId),HttpStatus.OK);
    }

    @GetMapping("/username/{username}/following")
    public ResponseEntity<Object> getUserFollowing(@PathVariable String username){
        return new ResponseEntity<>(connectionsService.getUserFollowing(username),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers(){
        return new ResponseEntity<>(connectionsService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/id/{uuid}")
    public ResponseEntity<Object> getUser(@PathVariable UUID uuid){
        return new ResponseEntity<>(connectionsService.getUser(uuid),HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Object> getUser(@PathVariable String username){
        return new ResponseEntity<>(connectionsService.getUser(username),HttpStatus.OK);
    }

    @GetMapping("/id/{userId}/followers")
    public ResponseEntity<Object> getUserFollowers(@PathVariable UUID userId){
        return new ResponseEntity<>(connectionsService.getUserFollowers(userId),HttpStatus.OK);
    }
    @GetMapping("/username/{username}/followers")
    public ResponseEntity<Object> getUserFollowers(@PathVariable String username){
        return new ResponseEntity<>(connectionsService.getUserFollowers(username),HttpStatus.OK);
    }

    @DeleteMapping("/id/{uuid}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID uuid){
        connectionsService.deleteUser(uuid);
        return new ResponseEntity<>("Deleted user successfully!",HttpStatus.OK);
    }
    @DeleteMapping("/username/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username){
        connectionsService.deleteUser(username);
        return new ResponseEntity<>("Deleted user successfully!",HttpStatus.OK);
    }
}

