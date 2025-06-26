package com.oracle.JournalApp.cache;

import com.oracle.JournalApp.Entry.configJournelAppEntry;
import com.oracle.JournalApp.Repository.configJournelAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Appcache {
    public Map<String, String> Appcache = new HashMap<>();

    @Autowired
    private configJournelAppRepo configJournelApprepo;
    @PostConstruct
    public void init(){
       List<configJournelAppEntry>all =  configJournelApprepo.findAll();
        for(configJournelAppEntry  configJournelAppEntry :all)
        {
            Appcache.put(configJournelAppEntry.getKey(), configJournelAppEntry.getValue());
        }

    }
}
