package com.journalapp.scheduler;

import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    public void fetchUseraAndSendSaMail(){
        List<User> users= userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries= user.getJournalEntries();
        }

    }
}
