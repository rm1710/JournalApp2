package com.JournalApp.service;

import com.JournalApp.entity.User;
import com.JournalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public boolean TestsaveNewUser(User user) {
        try{
            System.out.println(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            User us= userRepository.save(user);
            return true;
        }catch(Exception e){
            return false;
        }

    }

    public User saveNewUser(User user) {
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        User us= userRepository.save(user);
        return us;
    }

    public User saveUser(User user) {
        User saved = userRepository.save(user);
        System.out.println("Saved user: " + saved);
        return saved;
    }

    public User saveAdminUser(User user) {
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER,ADMIN"));
        User us= userRepository.save(user);
        return us;

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }


}
