package com.JournalApp.service;

import com.JournalApp.entity.JournalEntry;
import com.JournalApp.entity.User;
import com.JournalApp.repository.JournalEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger= LoggerFactory.getLogger(JournalEntryService.class);

    public void saveEntry(JournalEntry journalEntry, String userName) {
       try{
           User user= userService.findByUserName(userName);
           if(user==null){
               throw new RuntimeException("user not found with username"+userName);
           }
           journalEntry.setDate(LocalDateTime.now());
           journalEntry.setUser(user);
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           logger.info("journal entry is saved");
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
       }catch (Exception e){
            logger.info("that is fail");
           throw new RuntimeException("An error occured while saving the entry.",e);
       }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(String userName) {
        return journalEntryRepository.findByUser_UserName(userName);
    }

    public Optional<JournalEntry> findById(Long id) {
        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(Long id, String userName) {
        boolean removed= false;
        try{
            User user= userService.findByUserName(userName);
            removed= user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry.",e);
        }
        return removed;
    }

}