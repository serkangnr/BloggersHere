package com.serkanguner.bloggershere.controller;


import static com.serkanguner.bloggershere.constant.EndPoints.*;

import com.serkanguner.bloggershere.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ROOT+POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
}
