package com.oracle.JournalApp.Repo;

import com.oracle.JournalApp.Repository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserRepoImplTest {
    @Autowired
    UserRepoImpl userRepo;

    @Test
    public void testNewUser() {
        Assertions.assertNotNull(userRepo.getSAList());
    }

}
