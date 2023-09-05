package com.minitwitter.minitwitter.profiles.service;

import com.minitwitter.minitwitter.auth.model.Account;
import com.minitwitter.minitwitter.auth.repository.AccountRepository;
import com.minitwitter.minitwitter.profiles.model.Profile;
import com.minitwitter.minitwitter.profiles.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;
    private final TransactionTemplate transactionTemplate;

    public void addProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public Profile getProfile(String username) {
        return profileRepository.findById(username).get();
    }
    public void updateProfileEmail(String username,String email){
        Profile profile = getProfile(username);
        profile.setEmail(email);
        profileRepository.save(profile);
    }
    public void updateProfileFirstName(String username,String firstName){
        Profile profile = getProfile(username);
        profile.setFirstName(firstName);
        profileRepository.save(profile);
    }
    public void updateProfileLastName(String username,String lastName){
        Profile profile = getProfile(username);
        profile.setLastName(lastName);
        profileRepository.save(profile);
    }
    public void updateProfileBirthDate(String username, Date birthDate){
        Profile profile = getProfile(username);
        profile.setBirthDate(birthDate);
        profileRepository.save(profile);
    }
    @Transactional
    public void deleteAccount(String username){
        Account account = accountRepository.findById(username).get();
        Profile profile = profileRepository.findById(username).get();

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            public void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountRepository.delete(account);
                profileRepository.delete(profile);
            }
        });
    }


}
