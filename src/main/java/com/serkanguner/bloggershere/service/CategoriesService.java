package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.repository.CategoriesRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService extends ServiceManager<Categories,Long> {
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
    }
}
