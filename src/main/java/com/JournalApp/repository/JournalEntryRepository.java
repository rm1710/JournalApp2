package com.JournalApp.repository;

import com.JournalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findByUser_UserName(String userName);
}