package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.repository.UserRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceManager<User, Long> {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
