package com.journalapp.controller;

import com.journalapp.entity.JournalEntry;
import com.journalapp.entity.User;
import com.journalapp.service.JournalEntryService;
import com.journalapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntries() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user= userService.findByUserName(userName);
        List<JournalEntry> entries = user.getJournalEntries();

        if (entries!=null && entries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(entries, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName= authentication.getName();
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user= userService.findByUserName(userName);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> entry = journalEntryService.findById(id);
            if(entry.isPresent()){
                return new ResponseEntity<>(entry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable Long id) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        boolean removed=journalEntryService.deleteById(id,username);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable Long id,
                                        @RequestBody JournalEntry newEntry) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user= userService.findByUserName(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> entry = journalEntryService.findById(id);
            if(entry.isPresent()){
                JournalEntry old= entry.get();
                old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(entry.get(),HttpStatus.OK);
            }
        }
        JournalEntry old= journalEntryService.findById(id).orElse(null);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}