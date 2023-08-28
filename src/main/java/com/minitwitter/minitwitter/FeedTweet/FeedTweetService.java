package com.minitwitter.minitwitter.FeedTweet;

import com.minitwitter.minitwitter.connections.controller.ConnectionsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FeedTweetService {
    private final FeedTweetRepositry feedRepository;
    private final ConnectionsController connectionsController;
    @Autowired
    public FeedTweetService(FeedTweetRepositry feedRepository,
                            ConnectionsController connectionsController) {
        this.feedRepository = feedRepository;
        this.connectionsController = connectionsController;
    }
//    public List<FeedTweet> getTweetsByUsername(String username){
//        return feedRepository.getTweetsByUsername(username);
//    }

    public void addTweet(String followeeUserName,
                         UUID tweetid,
                         LocalDateTime createdAt,
                         String content,
                         List<String>mediaUrls){
         List<String> connection= connectionsController.
                 getUserFollowers(followeeUserName).
                 getHeaders().
                 getConnection();
        connection.forEach(user -> {
            FeedTweetPrimaryKey primaryKey = new FeedTweetPrimaryKey();
            primaryKey.setFollowerusername(followeeUserName);
            primaryKey.setTweetid(tweetid);
            primaryKey.setCreatedAt(createdAt);
            FeedTweet feedTweet = new FeedTweet(
                    primaryKey,
                    followeeUserName,
                    content,
                    mediaUrls);
            feedRepository.save(feedTweet);
        });
    }
    public void deleteTweet(String followeeUserName,
                            UUID tweetid,
                            LocalDateTime createdAt,
                            String content,
                            List<String>mediaUrls) {
        List<String> connection= connectionsController.
                getUserFollowers(followeeUserName).
                getHeaders().
                getConnection();
        connection.forEach(user -> {
            FeedTweetPrimaryKey primaryKey = new FeedTweetPrimaryKey();
            primaryKey.setFollowerusername(followeeUserName);
            primaryKey.setTweetid(tweetid);
            primaryKey.setCreatedAt(createdAt);

            FeedTweet feedTweet = new FeedTweet(
                    primaryKey,
                    followeeUserName,
                    content,
                    mediaUrls);
            feedRepository.delete(feedTweet);
        });
    }
    public void updateTweet(String followeeUserName,
                            UUID tweetid,
                            LocalDateTime createdAt,
                            String content,
                            List<String>mediaUrls) {
        List<String> connection= connectionsController.
                getUserFollowers(followeeUserName).
                getHeaders().
                getConnection();
        connection.forEach(user -> {
            FeedTweetPrimaryKey primaryKey = new FeedTweetPrimaryKey();
            primaryKey.setFollowerusername(followeeUserName);
            primaryKey.setTweetid(tweetid);
            primaryKey.setCreatedAt(createdAt);
            if(!feedRepository.existsById(primaryKey)){
                //throw error
            }
            feedRepository.findById(primaryKey).get().setContent(content);
            feedRepository.findById(primaryKey).get().setMediaUrls(mediaUrls);
        });
    }
}
