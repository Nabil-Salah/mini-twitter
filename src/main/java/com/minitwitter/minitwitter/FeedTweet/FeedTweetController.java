package com.minitwitter.minitwitter.FeedTweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feed")
public class FeedTweetController {
    private final FeedTweetService feedTweetService;

    @Autowired
    public FeedTweetController(FeedTweetService feedTweetService){
        this.feedTweetService = feedTweetService;
    }
    /*@GetMapping("/{username}")
    public List<FeedTweet> getFeedTweets(@PathVariable String username){
        return feedTweetService.getTweetsByUsername(username);
    }*/
    public void addTweet(String followeeUserName,
                         UUID tweetid,
                         LocalDateTime createdAt,
                         String content,
                         List<String>mediaUrls){
        feedTweetService.addTweet(
                followeeUserName,
                tweetid,
                createdAt,
                content,
                mediaUrls
        );
    }
    public void deleteTweet(String followeeUserName,
                            UUID tweetid,
                            LocalDateTime createdAt,
                            String content,
                            List<String>mediaUrls){
        feedTweetService.deleteTweet(
                followeeUserName,
                tweetid,
                createdAt,
                content,
                mediaUrls
        );
    }
    public void updateTweet(String followeeUserName,
                            UUID tweetid,
                            LocalDateTime createdAt,
                            String content,
                            List<String>mediaUrls){
        feedTweetService.updateTweet(
                followeeUserName,
                tweetid,
                createdAt,
                content,
                mediaUrls
        );
    }
}
