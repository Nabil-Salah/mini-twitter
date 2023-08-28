package com.minitwitter.minitwitter.FeedTweet;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface FeedTweetRepositry extends CassandraRepository<FeedTweet, FeedTweetPrimaryKey> {
    //List<FeedTweet> getTweetsByUsername(String username);
}


