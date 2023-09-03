package com.minitwitter.minitwitter.Tweets.FeedTweet;

import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweet;
import com.minitwitter.minitwitter.Tweets.HomeTweet.HomeTweetPrimaryKey;
import com.minitwitter.minitwitter.connections.controller.ConnectionsController;
import com.minitwitter.minitwitter.connections.model.User;
import com.minitwitter.minitwitter.connections.service.ConnectionsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FeedTweetService {
    private final FeedTweetRepositry feedRepository;
    private final ConnectionsService connectionsService;
    public List<FeedTweet> getTweetsByUsername(String username){
        return feedRepository.findFeedTweetsByUsername(username);
    }

    public void addTweet(HomeTweet homeTweet){
        HomeTweetPrimaryKey primaryKey = homeTweet.getPrimaryKey();
        Collection<User> followers = connectionsService.getUserFollowers(primaryKey.getUsername());
        followers.forEach(user -> {
            FeedTweetPrimaryKey newprimaryKey = new FeedTweetPrimaryKey();
            newprimaryKey.setFollowerusername(user.getUsername());
            newprimaryKey.setTweetid(primaryKey.getTweetid());
            newprimaryKey.setCreatedAt(primaryKey.getCreatedAt());
            FeedTweet feedTweet = new FeedTweet(
                    newprimaryKey,
                    primaryKey.getUsername(),
                    homeTweet.getContent(),
                    homeTweet.getMediaUrls());
            feedRepository.save(feedTweet);
        });
    }
    public void deleteTweet(HomeTweet homeTweet) {
        HomeTweetPrimaryKey primaryKey = homeTweet.getPrimaryKey();
        Collection<User> followers = connectionsService.getUserFollowers(primaryKey.getUsername());
        followers.forEach(user -> {
            FeedTweetPrimaryKey newprimaryKey = new FeedTweetPrimaryKey();
            newprimaryKey.setFollowerusername(user.getUsername());
            newprimaryKey.setTweetid(primaryKey.getTweetid());
            newprimaryKey.setCreatedAt(primaryKey.getCreatedAt());
            FeedTweet feedTweet = new FeedTweet(
                    newprimaryKey,
                    primaryKey.getUsername(),
                    homeTweet.getContent(),
                    homeTweet.getMediaUrls());
            feedRepository.delete(feedTweet);
        });
    }
    public void updateTweet(HomeTweet homeTweet) {
        HomeTweetPrimaryKey primaryKey = homeTweet.getPrimaryKey();
        Collection<User> followers = connectionsService.getUserFollowers(primaryKey.getUsername());
        followers.forEach(user -> {
            FeedTweetPrimaryKey newprimaryKey = new FeedTweetPrimaryKey();
            newprimaryKey.setFollowerusername(user.getUsername());
            newprimaryKey.setTweetid(primaryKey.getTweetid());
            newprimaryKey.setCreatedAt(primaryKey.getCreatedAt());
            FeedTweet feedTweet = feedRepository.findById(newprimaryKey).get();
            feedTweet.setContent(homeTweet.getContent());
            feedTweet.setMediaUrls(homeTweet.getMediaUrls());
            feedRepository.save(feedTweet);
        });
    }
}
