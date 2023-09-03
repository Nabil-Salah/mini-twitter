package com.minitwitter.minitwitter.Tweets.FeedTweet;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface FeedTweetRepositry extends CassandraRepository<FeedTweet, FeedTweetPrimaryKey> {
    @Query("SELECT * FROM feedtweet where followerusername = ?0")
    List<FeedTweet> findFeedTweetsByUsername(String username);
}


