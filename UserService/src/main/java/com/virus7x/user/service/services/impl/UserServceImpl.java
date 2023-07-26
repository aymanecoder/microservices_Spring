package com.virus7x.user.service.services.impl;

import com.virus7x.user.service.entities.Hotel;
import com.virus7x.user.service.entities.Rating;
import com.virus7x.user.service.entities.User;
import com.virus7x.user.service.exceptions.ResourceNotFoundException;
import com.virus7x.user.service.external.service.HotelService;
import com.virus7x.user.service.repositories.UserRepository;
import com.virus7x.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServceImpl implements UserService {

    private final UserRepository repository;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;
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

        Rating[] ratingForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingForUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
//            http://localhost:8082/hotels/94b4762d-0139-42f5-b76a-189cc4d58cc1
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                Hotel body = forEntity.getBody();
            log.info("status {}",forEntity.getStatusCode());
            rating.setHotel(body);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);


        log.info("{}",ratingForUser);
        return user;
    }
}
