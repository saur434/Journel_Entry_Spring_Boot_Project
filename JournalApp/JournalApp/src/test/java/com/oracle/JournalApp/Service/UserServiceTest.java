package com.oracle.JournalApp.Service;

import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Autowired
    private UserRepo userRepo;

    @Test
    public void testFindByUserName() {
        assertEquals(4, 2 + 2);
        UserEntry user = userRepo.findByuserName("ram");
        assertTrue(!user.getJournelEntries().isEmpty());

    }

}
