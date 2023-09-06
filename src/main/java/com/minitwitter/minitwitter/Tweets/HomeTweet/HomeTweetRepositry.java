package com.minitwitter.minitwitter.Tweets.HomeTweet;

import com.minitwitter.minitwitter.Tweets.FeedTweet.FeedTweet;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Repository
public interface HomeTweetRepositry extends CassandraRepository<HomeTweet, HomeTweetPrimaryKey> {

    @Query("SELECT count(*) from hometweet where username = ?0")
    Long existsByUsername(String username);

    @Query("SELECT * FROM hometweet where username = ?0")
    Optional<List<HomeTweet>> findHomeTweetByUsername(String username);

    @Query("SELECT * FROM hometweet where tweetid = ?0")
    Optional<HomeTweet> findHomeTweetByTweetID(UUID uuid);
    @Query("SELECT * FROM hometweet where username=?0 and tweetid = ?1 ALLOW FILTERING")
    Optional<HomeTweet> findByUsernameAndTweetid(String username,UUID tweetid);

    @Query("DELETE FROM hometweet where username=?0")
    void deleteAllByUsername(String username);


}


