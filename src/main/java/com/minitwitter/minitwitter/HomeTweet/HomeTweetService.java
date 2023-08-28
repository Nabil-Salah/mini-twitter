package com.minitwitter.minitwitter.HomeTweet;

import com.minitwitter.minitwitter.FeedTweet.FeedTweetController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class HomeTweetService {

    private final HomeTweetRepositry homeRepository;
    private final FeedTweetController feedTweetController;
    @Autowired
    public HomeTweetService(HomeTweetRepositry homeRepository,
                            FeedTweetController feedTweetController) {
        this.homeRepository = homeRepository;
        this.feedTweetController = feedTweetController;
    }
    public List<HomeTweet> getTweets(){
        return homeRepository.findAll();
    }
    public void addTweet(String username, Map<String, Object> tweet) {
        if(username.isEmpty()){
            //throw error
        }
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
                .content(tweet.get("content").toString())
                .mediaUrls((List<String>) tweet.get("urls"))
                .build();
        homeRepository.save(homeTweet);
        feedTweetController.addTweet(primaryKey.getUsername(),
                primaryKey.getTweetid(),
                primaryKey.getCreatedAt(),
                homeTweet.getContent(),
                homeTweet.getMediaUrls());
    }
    public void deleteTweet(String username,
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
        Optional<HomeTweet> homeTweet = homeRepository.findById(primaryKey);
        if(!homeTweet.isPresent()){
            //throw error
            return;
        }
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
        feedTweetController.deleteTweet(primaryKey.getUsername(),
                primaryKey.getTweetid(),
                primaryKey.getCreatedAt(),
                homeTweet.get().getContent(),
                homeTweet.get().getMediaUrls());
        homeRepository.deleteById(primaryKey);
    }
    public void updateTweet(String username,
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
        Optional<HomeTweet> homeTweet = homeRepository.findById(primaryKey);
        if(!homeTweet.isPresent()){
            //throw error
            return;
        }
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
        homeRepository.findById(primaryKey).get().
                setContent(tweet.get("content").toString());
        homeRepository.findById(primaryKey).get().
                setMediaUrls((List<String>) tweet.get("content"));
        feedTweetController.updateTweet(primaryKey.getUsername(),
                primaryKey.getTweetid(),
                primaryKey.getCreatedAt(),
                tweet.get("content").toString(),
                (List<String>) tweet.get("content"));
    }
}
