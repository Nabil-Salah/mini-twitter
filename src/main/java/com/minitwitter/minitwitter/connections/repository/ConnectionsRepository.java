package com.minitwitter.minitwitter.connections.repository;

import com.minitwitter.minitwitter.connections.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConnectionsRepository extends Neo4jRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query(
            "MATCH (follower:User {username: $follower}) " +
            "MATCH (following:User {username: $following})" +
            "CREATE (follower) -[:FOLLOWS]-> (following)"
    )
    void followUser(@Param("follower") String follower,@Param("following") String following);
    @Query(
            "MATCH (follower:User {id: $followerId}) " +
            "MATCH (following:User {id: $followingId})" +
            "CREATE (follower) -[:FOLLOWS]-> (following)"
    )
    void followUser(@Param("$followerId") UUID followerId,@Param("$followingId") UUID followingId);

    @Query(
            "MATCH (follower:User{username:$follower})" +
            "-[r:FOLLOWS]->" +
            "(following:User{username:$following}) " +
            "delete r"
    )
    void unFollowUser(@Param("follower") String follower,@Param("following") String following);


    @Query(
            "MATCH (follower:User{id:$followerId})" +
            "-[r:FOLLOWS]->" +
            "(following:User{id:$followingId}) " +
            "delete r"
    )
    void unFollowUser(@Param("followerId") UUID followerId,@Param("followingId") UUID followingId);

    @Query(
            "MATCH (followers:User)" +
            " -[:FOLLOWS]-> " +
            "(user:User{username: $username}) " +
            "return followers"
    )
    List<User> getFollowersByUsername(@Param("username") String username);
    @Query(
            "MATCH (followers:User)" +
                    " -[:FOLLOWS]-> " +
                    "(user:User{id: $uuid}) " +
                    "return followers"
    )
    List<User> getFollowersById(@Param("uuid") UUID uuid);


   /*@Query(
            "MATCH (following:User)" +
                    " <-[:FOLLOWS]- " +
                    "(user:User{username: $username}) " +
                    "return following"
    )
    Optional<List<User>> getFollowingByUsername(@Param("username") String username);

    @Query(
            "MATCH (following:User)" +
                    " <-[:FOLLOWS]- " +
                    "(user:User{id: uuid}) " +
                    "return following"
    )
    Optional<List<User>> getFollowingById(@Param("uuid") UUID uuid);


   @Query(
            "MATCH (user:User {id: $uuid}) DETACH DELETE user"
    )
    void deleteById(@Param("uuid") UUID uuid);*/


}