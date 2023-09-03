package com.minitwitter.minitwitter.Tweets.HomeTweet;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@PrimaryKeyClass
@Getter
@Setter
public class HomeTweetPrimaryKey {
    @PrimaryKeyColumn(name = "username", type = PrimaryKeyType.PARTITIONED)
    private String username;
    @PrimaryKeyColumn(name = "tweetid",ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @Generated
    private UUID tweetid;

    @PrimaryKeyColumn(name = "createdAt",ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private LocalDateTime createdAt;


}
