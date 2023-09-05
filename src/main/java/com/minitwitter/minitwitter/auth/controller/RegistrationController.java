package com.minitwitter.minitwitter.auth.controller;

import com.minitwitter.minitwitter.auth.model.UserDTO;
import com.minitwitter.minitwitter.auth.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid UserDTO user){
        registrationService.registerAccount(user);
        return new ResponseEntity<>("Your account has been created successfully!",HttpStatus.OK);
    }
    @GetMapping("/confirm-account/{confirmationToken}")
    public ResponseEntity<?> confirmUserAccount(@PathVariable String confirmationToken) {
        return registrationService.confirmEmail(confirmationToken);
    }

}
