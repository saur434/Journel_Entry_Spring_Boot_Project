package com.oracle.JournalApp.Service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void EmailTest() {
        emailService.sendEmail("saurabhdhande48@gmail.com", "Testing in progress", "Hii great successful");
    }
}
