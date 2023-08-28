package com.minitwitter.minitwitter.FeedTweet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
@Table
@Getter
@Setter
@AllArgsConstructor
@Builder
public class FeedTweet {
    @PrimaryKey
    private FeedTweetPrimaryKey primaryKey;

    @Column(value = "followeeusername")
    private String followeeusername;
    
    @Column(value = "content")
    private String content;

    @Column(value = "mediaUrls")
    private List<String> mediaUrls;

    @Override
    public String toString(){
        return "Student{" +
                "id=" + primaryKey.getFollowerusername() +
                "followee_id=" + getFolloweeusername() +
                "created_at=" + primaryKey.getCreatedAt() +
//                ", firstName=" + getFirstName() +
//                ", lastName=" + getLastName() +
                ", content='" + getContent() + '\'' +
//                ", profilePicUrl='" + getProfilePicUrl() + '\'' +
                ", mediaUrl='" + getMediaUrls() + '\'' +
                '}';
    }
}
