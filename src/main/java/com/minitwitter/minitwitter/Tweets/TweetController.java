package com.minitwitter.minitwitter.Tweets;

import com.minitwitter.minitwitter.Tweets.FeedTweet.FeedTweet;
import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweet;
import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TweetController {
    private final TweetService tweetService;

    @GetMapping("/home/{username}")
    public List<HomeTweet> getHomeTweets(@PathVariable String username){
        return tweetService.getHomeTweets(username);
    }
    @PostMapping("/home/{username}/add")
    public void registerNewTweet(@PathVariable String username,
                                 @RequestBody Map<String,Object> tweet) {
        tweetService.addTweet(username,tweet);
    }
    @DeleteMapping("/home/{username}/delete/{tweetid}")
    public void deleteTweet(@PathVariable String username,
                            @PathVariable UUID tweetid,
                            @RequestBody LocalDateTime createdAt,
                            @RequestBody Map<String,Object> tweet) {
        tweetService.deleteTweet(username,tweetid,createdAt,tweet);
    }
    @PutMapping("/home/{username}/update/{tweetid}")
    public void updateTweet(@PathVariable String username,
                            @PathVariable UUID tweetid,
                            @RequestBody LocalDateTime createdAt,
                            @RequestBody Map<String,Object> tweet) {
        tweetService.updateTweet(username, tweetid, createdAt, tweet);
    }
    @GetMapping("/feed/{username}")
    public List<FeedTweet> getFeedTweets(@PathVariable String username){
        return tweetService.getFeedTweets(username);
    }
}
