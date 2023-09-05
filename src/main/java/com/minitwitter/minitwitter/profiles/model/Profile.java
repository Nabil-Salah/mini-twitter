package com.minitwitter.minitwitter.profiles.model;

import com.minitwitter.minitwitter.auth.model.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user_profile")
@Getter
@Setter
@NoArgsConstructor
public class Profile {
    public Profile(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.birthDate = userDTO.getBirthDate();
    }
    @Id
    String username;
    String email;
    String firstName;
    String lastName;
    Date birthDate;
}
