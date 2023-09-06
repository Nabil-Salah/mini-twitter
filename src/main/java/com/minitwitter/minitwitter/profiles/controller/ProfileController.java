package com.minitwitter.minitwitter.profiles.controller;

import com.minitwitter.minitwitter.profiles.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getProfile(@PathVariable String username){
        return new ResponseEntity<>(profileService.getProfile(username), HttpStatus.OK);
    }

    @PutMapping("/{username}/email")
    public ResponseEntity<Object> updateProfileEmail(@PathVariable String username, @RequestBody HashMap.Entry<String,String> updated){
        profileService.updateProfileEmail(username,updated.getValue());
        return new ResponseEntity<>("Email updated successfully to " + updated.getValue() + " !",HttpStatus.OK);
    }
    @PutMapping("/{username}/fname")
    public ResponseEntity<Object> updateProfileFirstName(@PathVariable String username,@RequestBody HashMap.Entry<String,String> updated){
        profileService.updateProfileFirstName(username,updated.getValue());
        return new ResponseEntity<>("First name updated successfully to " + updated.getValue() + " !",HttpStatus.OK);
    }
    @PutMapping("/{username}/lname")
    public ResponseEntity<Object> updateProfileLastName(@PathVariable String username, @RequestBody HashMap.Entry<String,String> updated){
        profileService.updateProfileLastName(username,updated.getValue());
        return new ResponseEntity<>("Last name updated successfully to " + updated.getValue() + " !",HttpStatus.OK);
    }
    @PutMapping("/{username}/bdate")
    public ResponseEntity<Object> updateProfileBirthdate(@PathVariable String username, @RequestBody HashMap.Entry<String, Date> updated){
        profileService.updateProfileBirthDate(username,updated.getValue());
        return new ResponseEntity<>("Last name updated successfully to " + updated.getValue() + " !",HttpStatus.OK);
    }

}
