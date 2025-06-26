package com.oracle.JournalApp.Service;

import com.oracle.JournalApp.Entry.JournelEntry;
import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Repository.JournelEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournelEntryService {

    @Autowired
    private JournelEntryRepo journelEntryRepo;
    @Autowired
    private UserService userService;

    public void saveEntry(JournelEntry journelEntry) {
        journelEntryRepo.save(journelEntry);
    }


    public void saveEntry(JournelEntry journelEntry, String username) {
        UserEntry userEntry = userService.findByUserName(username);
        JournelEntry saved = journelEntryRepo.save(journelEntry);
        if (userEntry.getJournelEntries() == null) {
            userEntry.setJournelEntries(new ArrayList<>());
        }
        userEntry.getJournelEntries().add(saved);
        userService.saveEntry(userEntry);

    }

    public List<JournelEntry> getall() {
        return journelEntryRepo.findAll();
    }

    public Optional<JournelEntry> getEntryById(ObjectId id) {
        return journelEntryRepo.findById(id);
    }

    public boolean deleteByid(ObjectId id, String username) {
       boolean remove = false;
        UserEntry user = userService.findByUserName(username);
         remove = user.getJournelEntries().removeIf(x -> (x.getId().equals(id)));
        if(remove){
            userService.saveEntry(user);
            journelEntryRepo.deleteById(id);
        }
        return remove;

    }

}
