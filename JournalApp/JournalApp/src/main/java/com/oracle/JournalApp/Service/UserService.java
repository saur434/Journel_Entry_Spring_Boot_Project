package com.oracle.JournalApp.Service;


import com.oracle.JournalApp.Entry.UserEntry;

import com.oracle.JournalApp.Repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;


   private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(UserEntry userEntry) {
        userRepo.save(userEntry);
    }

    public void saveNewUser(UserEntry user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        } catch (Exception e) {
           log.info("Duplicate Key Found for {}",  user.getUserName());
           log.debug("Duplicate key found");
            log.trace("Duplicate key found");
        }

    }

    public void saveAdminUser(UserEntry user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepo.save(user);
    }

    public List<UserEntry> getall() {
        return userRepo.findAll();
    }

    public Optional<UserEntry> getEntryById(ObjectId id) {
        return userRepo.findById(id);
    }

    public void deleteByid(ObjectId id) {
        userRepo.deleteById(id);
    }

    public UserEntry findByUserName(String userName) {
        return userRepo.findByuserName(userName);
    }

}
