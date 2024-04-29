package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.utility.ServiceManager;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService extends ServiceManager<User, Long> {

    public UserService(JpaRepository<User, Long> jpaRepository) {
        super(jpaRepository);
    }
}
