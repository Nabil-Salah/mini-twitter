package com.minitwitter.minitwitter.Tweets;

import com.minitwitter.minitwitter.Tweets.FeedTweet.FeedTweet;
import com.minitwitter.minitwitter.Tweets.FeedTweet.FeedTweetService;
import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweet;
import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweetService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TweetService {
    private final FeedTweetService feedTweetService;
    private final HomeTweetService homeTweetService;
    public List<HomeTweet> getHomeTweets(String username){
        return homeTweetService.getTweets(username);
    }
    public List<FeedTweet> getFeedTweets(String username){
        return feedTweetService.getTweetsByUsername(username);
    }
    @Transactional
    public void addTweet(String username, Map<String,Object> tweet){
        HomeTweet homeTweet = homeTweetService.addTweet(
                username, tweet);
        feedTweetService.addTweet(homeTweet);
    }
    @Transactional
    public void deleteTweet(String username, UUID tweetid, LocalDateTime createdAt, Map<String,Object> tweet){
        HomeTweet homeTweet = homeTweetService.deleteTweet(
                username, tweetid, createdAt, tweet);
        feedTweetService.deleteTweet(homeTweet);
    }
    @Transactional
    public void updateTweet(String username, UUID tweetid, LocalDateTime createdAt, Map<String,Object> tweet){
        HomeTweet homeTweet = homeTweetService.updateTweet(
                username, tweetid, createdAt, tweet);
        feedTweetService.updateTweet(homeTweet);
    }

}
