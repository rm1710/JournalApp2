package com.journalapp.repository;

import com.journalapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    User findByUserName(String userName);

    void deleteByUserName(String name);
}
