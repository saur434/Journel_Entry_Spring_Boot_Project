package com.oracle.JournalApp.Controller;

import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Repository.UserRepo;
import com.oracle.JournalApp.Service.UserService;
import com.oracle.JournalApp.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
@Autowired
private WeatherService weatherService;
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry updatedEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry userInDb = userService.findByUserName(userName);

        if (userInDb != null) {
            userInDb.setUserName((updatedEntry.getUserName()));
            userInDb.setPassword(updatedEntry.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteuserbyId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepo.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hi " + authentication.getName() + " Weather Feels like " + weatherService.getWeather("Mumbai").getCurrent().getTemperature() + " degree celsisus", HttpStatus.OK);
    }

}
