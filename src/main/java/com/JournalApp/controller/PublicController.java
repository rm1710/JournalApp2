package com.journalapp.controller;


import com.journalapp.entity.User;
import com.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;


    @GetMapping("/heath-check")
    public String heathCheck(){
        return "ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveNewUser(user), HttpStatus.CREATED);
    }


}
