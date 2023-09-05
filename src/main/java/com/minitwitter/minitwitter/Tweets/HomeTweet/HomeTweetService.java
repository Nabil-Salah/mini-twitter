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
        LocalDateTime nowTime = LocalDateTime.now();
        HomeTweetPrimaryKey primaryKey = new HomeTweetPrimaryKey();
        primaryKey.setUsername(username);
        primaryKey.setCreatedAt(nowTime);
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
                .content(tweet.get("content").toString()).tweetid(UUID.randomUUID())
                .mediaUrls((List<String>) tweet.get("urls"))
                .build();
        homeRepository.save(homeTweet);
        return homeTweet;
    }
    public HomeTweet deleteTweet(String username,
                            UUID tweetid) {
        HomeTweet homeTweet = homeRepository.findByUsernameAndTweetid(username,tweetid).get();
        homeRepository.deleteById(homeTweet.getPrimaryKey());
        return homeTweet;
    }

    public HomeTweet updateTweet(String username,
                            UUID tweetid,
                            Map<String,Object> tweet){
        HomeTweet homeTweet = homeRepository.findByUsernameAndTweetid(username, tweetid).get();
        if(tweet.containsKey("content")){
            if(tweet.get("content") instanceof String){
                homeTweet.setContent(tweet.get("content").toString());
            }else{
                //throw error
            }
        }
        if(tweet.containsKey("urls")){
            if(tweet.get("urls") instanceof List){
                homeTweet.setMediaUrls((List<String>) tweet.get("urls"));
            }else{
                //throw error
            }
        }
        homeRepository.save(homeTweet);
        return homeTweet;
    }
}
