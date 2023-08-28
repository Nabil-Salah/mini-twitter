package com.minitwitter.minitwitter.connections.repository;

import com.minitwitter.minitwitter.connections.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionsRepository extends Neo4jRepository<User, String> {

    @Query(
            "MATCH (follower:User {username: $follower}) " +
            "MATCH (following:User {username: $following})" +
            "CREATE (follower) -[:FOLLOWS]-> (following)"
    )
    void followUser(@Param("follower") String follower,@Param("following") String following);

    @Query(
            "MATCH (follower:User{username:$follower})" +
            "-[r:FOLLOWS]->" +
            "(following:User{username:$following}) " +
            "delete r"
    )
    void unFollowUser(@Param("follower") String follower,@Param("following") String following);



    @Query(
            "MATCH (followers:User)" +
            " -[:FOLLOWS]-> " +
            "(user:User{username: $username}) " +
            "return followers"
    )
    List<User> getFollowersByUsername(@Param("username") String username);


}