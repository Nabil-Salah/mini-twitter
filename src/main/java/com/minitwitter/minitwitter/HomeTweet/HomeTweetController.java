package com.minitwitter.minitwitter.HomeTweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/home")
public class HomeTweetController {
    private final HomeTweetService homeService;

    @Autowired
    public HomeTweetController(HomeTweetService homeService) {
        this.homeService = homeService;
    }
    @GetMapping
    public List<HomeTweet> getTweets(){
        return homeService.getTweets();
    }
    @PostMapping("/{username}/add")
    public void registerNewTweet(@PathVariable String username,
                                 @RequestBody Map<String,Object> tweet) {
        homeService.addTweet(username,tweet);
    }
    @PostMapping("{username}/delete/{tweetid}")
    public void deleteTweet(@PathVariable String username,
                            @PathVariable UUID tweetid,
                            @RequestBody LocalDateTime createdAt,
                            @RequestBody Map<String,Object> tweet) {
        homeService.deleteTweet(username,tweetid,createdAt,tweet);
    }
    @PutMapping("{username}/update/{tweetid}")
    public void updateTweet(@PathVariable String username,
                            @PathVariable UUID tweetid,
                            @RequestBody LocalDateTime createdAt,
                            @RequestBody Map<String,Object> tweet) {
        homeService.updateTweet(username,tweetid,createdAt,tweet);
    }
}
