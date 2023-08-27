package com.example.twitterconnections.service;

import com.example.twitterconnections.exception.UserAlreadyFollowedException;
import com.example.twitterconnections.exception.UserNotFollowedException;
import com.example.twitterconnections.model.User;
import com.example.twitterconnections.repository.ConnectionsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ConnectionsService {

    private final ConnectionsRepository connectionsRepository;

    public void addUser(User user){
        connectionsRepository.save(user);
    }
    public User getUser(UUID uuid){
        return connectionsRepository.findById(uuid).get();
    }
    public User getUser(String username){
        return connectionsRepository.findByUsername(username).get();
    }
    public Iterable<User> getAllUsers(){
        return connectionsRepository.findAll();
    }

    public void deleteUser(UUID uuid){
        User toBeDeletedUser = getUser(uuid);
        connectionsRepository.delete(toBeDeletedUser);
    }
    public void deleteUser(String username){
        User toBeDeletedUser = getUser(username);
        connectionsRepository.delete(toBeDeletedUser);
    }

    @Transactional
    public void followUser(UUID followerId,UUID followingId){
        User follower = connectionsRepository.findById(followerId).get();
        User following = connectionsRepository.findById(followingId).get();

        follower.getFollowing().forEach(user -> {
            if (user.getId().equals(following.getId())) {
                throw new UserAlreadyFollowedException();
            }
        });

        follower.getFollowing().add(following);

        connectionsRepository.followUser(followerId,followingId);
    }

    @Transactional
    public void followUser(String followerName,String followingName){
        User follower = connectionsRepository.findByUsername(followerName).get();
        User following = connectionsRepository.findByUsername(followingName).get();

        follower.getFollowing().forEach(user -> {
            if (user.getId().equals(following.getId())) {
                throw new UserAlreadyFollowedException();
            }
        });

        follower.getFollowing().add(following);

        connectionsRepository.followUser(followerName,followingName);
    }
    @Transactional
    public void unFollowUser(UUID followerId,UUID followingId){
        User follower = connectionsRepository.findById(followerId).get();
        User following = connectionsRepository.findById(followingId).get();

        if(!follower.getFollowing().removeIf(user -> user.getId().equals(following.getId())))
            throw new UserNotFollowedException();

        connectionsRepository.unFollowUser(followerId,followingId);
    }
    @Transactional
    public void unFollowUser(String followerName,String followingName){
        User follower = connectionsRepository.findByUsername(followerName).get();
        User following = connectionsRepository.findByUsername(followingName).get();

        if(!follower.getFollowing().removeIf(user -> user.getId().equals(following.getId())))
            throw new UserNotFollowedException();

        connectionsRepository.unFollowUser(followerName,followingName);
    }
    public Iterable<User> getUserFollowers(UUID uuid){
        connectionsRepository.findById(uuid).get();
        return connectionsRepository.getFollowersById(uuid);
    }
    public Iterable<User> getUserFollowers(String username){
        connectionsRepository.findByUsername(username).get();
        return connectionsRepository.getFollowersByUsername(username);
    }
    public Iterable<User> getUserFollowing(UUID uuid){

        return connectionsRepository.findById(uuid).get().getFollowing();
    }
    public Iterable<User> getUserFollowing(String username){
        return connectionsRepository.findByUsername(username).get().getFollowing();
    }

}
