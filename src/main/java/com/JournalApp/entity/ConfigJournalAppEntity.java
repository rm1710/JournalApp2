package com.journalapp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="config_journal_app")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {
    @Id
    private String key;
    private String value;
}
