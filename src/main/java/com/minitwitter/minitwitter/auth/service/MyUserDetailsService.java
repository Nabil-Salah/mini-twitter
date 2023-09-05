package com.minitwitter.minitwitter.auth.service;

import com.minitwitter.minitwitter.auth.model.Account;
import com.minitwitter.minitwitter.auth.repository.AccountRepository;
import com.minitwitter.minitwitter.auth.model.MyUserDetails;
import com.minitwitter.minitwitter.exceptions.registration.EmailNotVerifiedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    public MyUserDetailsService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
        if(!account.isEmailVerified())
            throw new EmailNotVerifiedException("User " + username + "'s email is not verified!");
        return new MyUserDetails(account);
    }
}
