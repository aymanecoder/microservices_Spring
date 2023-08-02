package com.virus7x.user.service.controllers;


import com.virus7x.user.service.entities.User;
import com.virus7x.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        log.info("Fallback is executed because the service is down:",ex.getMessage());
        User user = User.builder().email("aymane@gmail.com").name("aymane").about("this user created aymane because some service down")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> aLlUser = userService.getAllUser();
        return ResponseEntity.ok(aLlUser);
    }

}
