package com.minitwitter.minitwitter.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;



@Entity
@Table(name="confirmationToken")
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String email;

    public ConfirmationToken(String email) {
        this.email = email;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


}