package com.minitwitter.minitwitter.auth.service;

import com.minitwitter.minitwitter.auth.model.ConfirmationToken;
import com.minitwitter.minitwitter.auth.repository.ConfirmationTokenRepository;
import com.minitwitter.minitwitter.connections.model.User;
import com.minitwitter.minitwitter.connections.repository.ConnectionsRepository;
import com.minitwitter.minitwitter.connections.service.ConnectionsService;
import com.minitwitter.minitwitter.exceptions.registration.UsernameAlreadyTakenException;
import com.minitwitter.minitwitter.auth.model.UserDTO;
import com.minitwitter.minitwitter.auth.repository.AccountRepository;
import com.minitwitter.minitwitter.auth.model.Account;
import com.minitwitter.minitwitter.profiles.model.Profile;
import com.minitwitter.minitwitter.profiles.repository.ProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;
    private final ConnectionsRepository connectionsRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransactionTemplate transactionTemplate;

    private final EmailConfirmationService emailConfirmationService;

    public void registerAccount(UserDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        Account account = new Account(user);
        if(accountRepository.findById(account.getUsername()).isPresent())
            throw new UsernameAlreadyTakenException();
        account.setRole("ROLE_USER");
        Profile profile = new Profile(user);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            public void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                profileRepository.save(profile);
                accountRepository.save(account);
                connectionsRepository.save(new User(profile.getUsername()));

                ConfirmationToken confirmationToken = new ConfirmationToken(account.getEmail());

                confirmationTokenRepository.save(confirmationToken);

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(account.getEmail());
                mailMessage.setSubject("Complete Registration!");
                mailMessage.setText("To confirm your account, please click here : "
                        +"http://localhost:8081/register/confirm-account/"+confirmationToken.getConfirmationToken());
                emailConfirmationService.sendEmail(mailMessage);

            }
        });

    }

    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken).get();
        try {
            Account account = accountRepository.findByEmail(token.getEmail()).get();
            account.setEmailVerified(true);
            accountRepository.save(account);
            confirmationTokenRepository.delete(token);
            return ResponseEntity.ok("Email verified successfully!");
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("Error: Couldn't verify email");
        }
    }

}
