package com.oracle.JournalApp.Controller;


import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Service.UserDetailsServiceImpl;
import com.oracle.JournalApp.Service.UserService;
import com.oracle.JournalApp.Utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    private ResponseEntity<?> signup(@Validated @RequestBody UserEntry userEntry) {

        try {
            userService.saveNewUser(userEntry);
            return new ResponseEntity<>(userEntry, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    private ResponseEntity<?> login(@Validated @RequestBody UserEntry userEntry) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntry.getUserName(), userEntry.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEntry.getUserName());
            String Jwttoken = jwtUtil.generateToken(userEntry.getUserName());
            return new ResponseEntity<>(Jwttoken, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
