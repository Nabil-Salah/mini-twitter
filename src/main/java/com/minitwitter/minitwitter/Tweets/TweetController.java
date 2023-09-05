package com.minitwitter.minitwitter.Tweets;

import com.minitwitter.minitwitter.Tweets.FeedTweet.FeedTweet;
import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweet;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
                            @PathVariable UUID tweetid) {
        tweetService.deleteTweet(username,tweetid);
    }
    @PutMapping("/home/{username}/update/{tweetid}")
    public void updateTweet(@PathVariable String username,
                            @PathVariable UUID tweetid,
                            @RequestBody Map<String,Object> tweet) {
        tweetService.updateTweet(username, tweetid, tweet);
    }
    @GetMapping("/feed/{username}")
    public List<FeedTweet> getFeedTweets(@PathVariable String username){
        return tweetService.getFeedTweets(username);
    }
}
