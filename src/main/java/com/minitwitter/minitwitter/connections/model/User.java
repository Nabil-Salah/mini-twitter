package com.minitwitter.minitwitter.connections.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.cypherdsl.core.Use;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Node("User")
@Getter
@Setter
@ToString()
public class User {

    @Id
    private String username;

    public User(String username){
        this.username = username;
    }

    @JsonIgnore
    @ToString.Exclude
    @Relationship(type = "FOLLOWS",direction = Relationship.Direction.OUTGOING)
    private Collection<User> following;
    
}