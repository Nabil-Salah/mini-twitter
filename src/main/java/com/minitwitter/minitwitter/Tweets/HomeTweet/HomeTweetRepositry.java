package com.minitwitter.minitwitter.Tweets.HomeTweet;

import com.minitwitter.minitwitter.Tweets.FeedTweet.FeedTweet;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface HomeTweetRepositry extends CassandraRepository<HomeTweet, HomeTweetPrimaryKey> {
    @Query("SELECT * FROM hometweet where username = ?0")
    List<HomeTweet> findHomeTweetByUsername(String username);
}


