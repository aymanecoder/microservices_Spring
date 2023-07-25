package com.virus7x.user.service.controllers;


import com.virus7x.user.service.entities.User;
import com.virus7x.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> aLlUser = userService.getAllUser();
        return ResponseEntity.ok(aLlUser);
    }

}
