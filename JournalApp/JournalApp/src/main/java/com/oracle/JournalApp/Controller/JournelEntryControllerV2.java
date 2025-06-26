package com.oracle.JournalApp.Controller;

import com.oracle.JournalApp.Entry.JournelEntry;
import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Service.JournelEntryService;
import com.oracle.JournalApp.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journel")
@Slf4j
public class JournelEntryControllerV2 {


    @Autowired
    private JournelEntryService journelEntryService;
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getallJorunelEntryByusername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);
        List<JournelEntry> all = user.getJournelEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournelEntry myentry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        myentry.setDate(LocalDateTime.now());
        try {
            journelEntryService.saveEntry(myentry, userName);

            return new ResponseEntity<>(myentry, HttpStatus.CREATED);
        } catch (Exception e) {
            log.debug("Bad Request", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getEntryAtID(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);
        List<JournelEntry> collect = user.getJournelEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournelEntry> journelEntry = journelEntryService.getEntryById(myid);
            if (journelEntry.isPresent()) {
                return new ResponseEntity<>(journelEntryService.getEntryById(myid), HttpStatus.FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> DeleteEntry(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean res = journelEntryService.deleteByid(myid, userName);
        if (res) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> UpdateEntryById(@PathVariable ObjectId myid, @RequestBody JournelEntry newEntry) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);

        List<JournelEntry> collect = user.getJournelEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournelEntry> journelEntry = journelEntryService.getEntryById(myid);
            JournelEntry old = journelEntry.get();
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals(" ") ? newEntry.getTitle() : old.getTitle());

            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals((" ")) ? newEntry.getContent() : old.getContent());
            journelEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
