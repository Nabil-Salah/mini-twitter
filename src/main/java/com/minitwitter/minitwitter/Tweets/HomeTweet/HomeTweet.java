package com.minitwitter.minitwitter.Tweets.HomeTweet;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Table
@Getter
@Setter
@AllArgsConstructor
@Builder
public class HomeTweet {
    @PrimaryKey
    private HomeTweetPrimaryKey primaryKey;

    @Column(value = "tweetid")
    private UUID tweetid;

    @Column(value = "content")
    private String content;

    @Column(value = "mediaUrls")
    private List<String> mediaUrls;

    @Override
    public String toString(){
        return "Student{" +
                "id=" + primaryKey.getUsername() +
                "created_at=" + primaryKey.getCreatedAt() +
//                ", firstName=" + getFirstName() +
//                ", lastName=" + getLastName() +
                ", content='" + getContent() + '\'' +
//                ", profilePicUrl='" + getProfilePicUrl() + '\'' +
                ", mediaUrl='" + getMediaUrls() + '\'' +
                '}';
    }
}
