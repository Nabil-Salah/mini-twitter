package com.minitwitter.minitwitter.connections.service;

import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweetService;
import com.minitwitter.minitwitter.connections.exception.UserAddedBeforeException;
import com.minitwitter.minitwitter.connections.exception.UserAlreadyFollowedException;
import com.minitwitter.minitwitter.connections.exception.UserNotFollowedException;
import com.minitwitter.minitwitter.connections.model.User;
import com.minitwitter.minitwitter.connections.repository.ConnectionsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConnectionsService {

    private final ConnectionsRepository connectionsRepository;
    private final KafkaTemplate<String,String> kafkaTemplate;
    public void addUser(User user) throws Exception {
        //TODO : To be handled later
        if(connectionsRepository.findById(user.getUsername()).isPresent())
            throw new UserAddedBeforeException();
        connectionsRepository.save(user);
    }

    public User getUser(String username){
        return connectionsRepository.findById(username).get();
    }
    public Iterable<User> getAllUsers(){
        return connectionsRepository.findAll();
    }

    public void deleteUser(String username){
        User toBeDeletedUser = getUser(username);
        connectionsRepository.delete(toBeDeletedUser);
    }

    @Transactional
    public void followUser(String followerName,String followingName){
        User follower = connectionsRepository.findById(followerName).get();
        User following = connectionsRepository.findById(followingName).get();

        follower.getFollowing().forEach(user -> {
            if (user.getUsername().equals(following.getUsername())) {
                throw new UserAlreadyFollowedException();
            }
        });

        follower.getFollowing().add(following);

        connectionsRepository.followUser(followerName,followingName);

        //TODO : send message containing follower,following
        kafkaTemplate.send("follow",followerName,followingName);

    }

    @Transactional
    public void unFollowUser(String followerName,String followingName){
        User follower = connectionsRepository.findById(followerName).get();
        User following = connectionsRepository.findById(followingName).get();

        if(!follower.getFollowing().removeIf(user -> user.getUsername().equals(following.getUsername())))
            throw new UserNotFollowedException();

        connectionsRepository.unFollowUser(followerName,followingName);
    }

    public Collection<User> getUserFollowers(String username){
        connectionsRepository.findById(username).get();
        return connectionsRepository.getFollowersByUsername(username);
    }
    public Collection<User> getUserFollowing(String username){
        return connectionsRepository.findById(username).get().getFollowing();
    }

}
