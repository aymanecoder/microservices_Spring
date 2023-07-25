package com.virus7x.user.service.services.impl;

import com.virus7x.user.service.entities.Rating;
import com.virus7x.user.service.entities.User;
import com.virus7x.user.service.exceptions.ResourceNotFoundException;
import com.virus7x.user.service.repositories.UserRepository;
import com.virus7x.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServceImpl implements UserService {

    private final UserRepository repository;
    private final RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = repository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server!! : "+userId));

        ArrayList<Rating> ratingForUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
//        List<Rating> ratingList = ratingForUser.stream().map(rating -> {
//
//        }).collect(Collectors.toList());
        user.setRatings(ratingForUser);
        log.info("{}",ratingForUser);
        return user;
    }
}
