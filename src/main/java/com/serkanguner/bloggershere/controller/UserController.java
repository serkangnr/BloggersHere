package com.serkanguner.bloggershere.controller;

import static com.serkanguner.bloggershere.constant.EndPoints.*;

import com.serkanguner.bloggershere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ROOT+USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
