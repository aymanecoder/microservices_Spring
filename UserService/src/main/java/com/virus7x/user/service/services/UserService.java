package com.virus7x.user.service.services;


import com.virus7x.user.service.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {

    User saveUser(User user);
    List<User> getAllUser();

    User getUser(String userId);
}
