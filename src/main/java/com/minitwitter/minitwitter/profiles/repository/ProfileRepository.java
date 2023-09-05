package com.minitwitter.minitwitter.profiles.repository;

import com.minitwitter.minitwitter.profiles.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile,String> {
}
