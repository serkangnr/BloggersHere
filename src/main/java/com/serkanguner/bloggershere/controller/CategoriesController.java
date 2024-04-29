package com.serkanguner.bloggershere.controller;

import static com.serkanguner.bloggershere.constant.EndPoints.*;

import com.serkanguner.bloggershere.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ROOT+CATEGORIES)
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
}
