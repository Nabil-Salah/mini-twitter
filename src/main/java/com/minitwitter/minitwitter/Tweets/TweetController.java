package com.minitwitter.minitwitter.Tweets;

import com.minitwitter.minitwitter.Tweets.FeedTweet.FeedTweet;
import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweet;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> registerNewTweet(@PathVariable String username,
                                                   @RequestBody Map<String,Object> tweet) {
        tweetService.addTweet(username,tweet);
        return new ResponseEntity<>("Tweeted successfully! ", HttpStatus.OK);
    }
    @DeleteMapping("/home/{username}/delete/{tweetid}")
    public ResponseEntity<Object> deleteTweet(@PathVariable String username,
                            @PathVariable UUID tweetid) {
        tweetService.deleteTweet(username,tweetid);
        return new ResponseEntity<>("Detweeted successfully! ",HttpStatus.OK);
    }
    @PutMapping("/home/{username}/update/{tweetid}")
    public ResponseEntity<Object> updateTweet(@PathVariable String username,
                            @PathVariable UUID tweetid,
                            @RequestBody Map<String,Object> tweet) {
        tweetService.updateTweet(username, tweetid, tweet);
        return new ResponseEntity<>("Updated successfully!",HttpStatus.OK);
    }
    @GetMapping("/feed/{username}")
    public List<FeedTweet> getFeedTweets(@PathVariable String username){
        return tweetService.getFeedTweets(username);
    }
}
