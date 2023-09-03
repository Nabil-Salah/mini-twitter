package com.minitwitter.minitwitter.Tweets.HomeTweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class HomeTweetService {

    private final HomeTweetRepositry homeRepository;
    @Autowired
    public HomeTweetService(HomeTweetRepositry homeRepository) {
        this.homeRepository = homeRepository;
    }
    public List<HomeTweet> getTweets(String username){
        return homeRepository.findHomeTweetByUsername(username);
    }
    public HomeTweet addTweet(String username, Map<String, Object> tweet) {
        if(username.isEmpty()){
            //throw error
        }
        LocalDateTime nowTime = LocalDateTime.now();
        HomeTweetPrimaryKey primaryKey = new HomeTweetPrimaryKey();
        primaryKey.setUsername(username);
        primaryKey.setCreatedAt(nowTime);
        primaryKey.setTweetid(UUID.randomUUID());
        if(!tweet.containsKey("content")){
            //throw error
        }else if(!(tweet.get("content") instanceof String)){
            //throw error
        }
        if(!tweet.containsKey("urls")){
            //throw error
        }else if(!(tweet.get("urls") instanceof List)){
            //throw error
        }
        HomeTweet homeTweet = HomeTweet.builder()
                        .primaryKey(primaryKey)
                .content(tweet.get("content").toString())
                .mediaUrls((List<String>) tweet.get("urls"))
                .build();
        homeRepository.save(homeTweet);
        return homeTweet;
    }
    public HomeTweet deleteTweet(String username,
                            UUID tweetid,
                            LocalDateTime createdAt,
                            Map<String,Object> tweet) {
        if(username.isEmpty()){
            //throw error
        }
        HomeTweetPrimaryKey primaryKey = new HomeTweetPrimaryKey();
        primaryKey.setUsername(username);
        primaryKey.setCreatedAt(createdAt);
        primaryKey.setTweetid(tweetid);
        HomeTweet homeTweet = homeRepository.findById(primaryKey).get();
        if(!tweet.containsKey("content")){
            //throw error
        }else if(!(tweet.get("content") instanceof String)){
            //throw error
        }
        if(!tweet.containsKey("urls")){
            //throw error
        }else if(!(tweet.get("urls") instanceof List)){
            //throw error
        }
        homeRepository.deleteById(primaryKey);
        return homeTweet;
    }
    public HomeTweet updateTweet(String username,
                            UUID tweetid,
                            LocalDateTime createdAt,
                            Map<String,Object> tweet){
        if(username.isEmpty()){
            //throw error
        }
        HomeTweetPrimaryKey primaryKey = new HomeTweetPrimaryKey();
        primaryKey.setUsername(username);
        primaryKey.setCreatedAt(createdAt);
        primaryKey.setTweetid(tweetid);
        HomeTweet homeTweet = homeRepository.findById(primaryKey).get();
        if(!tweet.containsKey("content")){
            //throw error
        }else if(!(tweet.get("content") instanceof String)){
            //throw error
        }
        if(!tweet.containsKey("urls")){
            //throw error
        }else if(!(tweet.get("urls") instanceof List)){
            //throw error
        }
        homeTweet.setContent(tweet.get("content").toString());
        homeTweet.setMediaUrls((List<String>) tweet.get("urls"));
        homeRepository.save(homeTweet);
        return homeTweet;
    }
}
