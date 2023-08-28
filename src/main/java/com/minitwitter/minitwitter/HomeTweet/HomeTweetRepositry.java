package com.minitwitter.minitwitter.HomeTweet;

import org.springframework.data.cassandra.repository.CassandraRepository;

@org.springframework.stereotype.Repository
public interface HomeTweetRepositry extends CassandraRepository<HomeTweet, HomeTweetPrimaryKey> {

}


