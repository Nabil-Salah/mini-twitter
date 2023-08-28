package com.minitwitter.minitwitter.FeedTweet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@PrimaryKeyClass
@Getter
@Setter
public class FeedTweetPrimaryKey {
    @PrimaryKeyColumn(name = "followerusername",ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String followerusername;

    @PrimaryKeyColumn(name = "tweetid",ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    @GeneratedValue
    private UUID tweetid;

    @PrimaryKeyColumn(name = "createdAt", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime createdAt;
}
