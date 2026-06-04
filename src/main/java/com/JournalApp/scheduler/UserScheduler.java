package com.journalapp.scheduler;

import com.journalapp.cache.AppCache;
import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.repository.UserRepositoryImpl;
import com.journalapp.service.EmailService;
import com.journalapp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron="0 * * ? * *")
    public void fetchUsersAndSendSaMail(){
        List<User> users= userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries= user.getJournalEntries();
            List<String> filteredEntries=journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ",filteredEntries);
            String sentiment = String.valueOf(sentimentAnalysisService.getSentiment(entry));
            emailService.sendEmail(user.getEmail(),"Sentiment for last 7days",sentiment);
        }
    }

    @Scheduled(cron="0 0 9 * * SUN")
    public void clearAppCache(){
        appCache.init();
    }
}
