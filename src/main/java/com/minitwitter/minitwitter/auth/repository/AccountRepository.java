package com.minitwitter.minitwitter.auth.repository;

import com.minitwitter.minitwitter.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findByEmail(String email);
    Boolean existsAccountByEmail(String email);

}
