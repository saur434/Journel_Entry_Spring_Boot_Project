package com.oracle.JournalApp.Service;

import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTest {
    @Mock
    UserRepo userRepo;


    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername() {
        when(userRepo.findByuserName(anyString()))
                .thenReturn(UserEntry.builder().userName("ram").password("hfkee").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");

    }
}
