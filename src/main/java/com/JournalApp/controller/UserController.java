package com.journalapp.controller;

import com.journalapp.api.response.WeatherResponse;
import com.journalapp.entity.User;
import com.journalapp.repository.UserRepository;
import com.journalapp.service.UserService;
import com.journalapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private WeatherService weatherService;
    public UserController(WeatherService weatherService){
        this.weatherService=weatherService;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User userInDb= userService.findByUserName(user.getUserName());
        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(
            @RequestParam String city) {
        return weatherService.getWeather(city);
    }
}
