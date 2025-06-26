package com.oracle.JournalApp.HealthCheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationHealthCheck {

    @GetMapping("/ok")
    public String GetHealthCheck(){
        return "Application is running";
    }
}
