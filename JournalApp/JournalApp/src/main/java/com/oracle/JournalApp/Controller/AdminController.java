package com.oracle.JournalApp.Controller;


import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Repository.UserRepo;
import com.oracle.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/all-users")
    public ResponseEntity<?> GetuserList() {

        List<UserEntry> users = userService.getall();
        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-admin")
    public ResponseEntity<?> AddAdmin(@RequestBody UserEntry user){

        userService.saveAdminUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
