package com.oracle.JournalApp.Scheduler;

import com.oracle.JournalApp.Entry.JournelEntry;
import com.oracle.JournalApp.Entry.UserEntry;
import com.oracle.JournalApp.Repository.UserRepoImpl;
import com.oracle.JournalApp.Service.EmailService;
import com.oracle.JournalApp.Service.SentimentAnalysisService;
import com.oracle.JournalApp.cache.Appcache;
import com.oracle.JournalApp.enums.Sentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private Appcache appcache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchuserandsendmail() {
        List<UserEntry> users = userRepo.getSAList();
        for (UserEntry user : users) {
            List<JournelEntry> journelEntries = user.getJournelEntries();
            List<Sentiment> sentiments = journelEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment Analysis for last 7 days: ", mostFrequentSentiment.toString());
            }


        }
    }


    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearappcache() {
        appcache.init();
    }
}
