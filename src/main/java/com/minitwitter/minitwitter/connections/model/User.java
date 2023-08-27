package com.minitwitter.minitwitter.connections.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;
import java.util.UUID;

@Node("User")
@Getter
@Setter
@ToString()
public class User {

    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private UUID id;

    private String username;

    @JsonIgnore
    @ToString.Exclude
    @Relationship(type = "FOLLOWS",direction = Relationship.Direction.OUTGOING)
    private Set<User> following;

}