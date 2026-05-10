package com.journalapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @Column(length = 1000)
    private String content;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id") //foreign key column
    private User user;
}