package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.dto.request.CategorySaveRequestDto;
import com.serkanguner.bloggershere.dto.request.UserSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CategoryFindAllResponseDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.mapper.CategoriesMapper;
import com.serkanguner.bloggershere.mapper.UserMapper;
import com.serkanguner.bloggershere.repository.CategoriesRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService extends ServiceManager<Categories,Long> {
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
    }

    public void categoriesSaveDto(CategorySaveRequestDto categorySaveRequestDto) {
        categoriesRepository.save(CategoriesMapper.INSTANCE.categoriesSaveRequestDtoToCategories(categorySaveRequestDto));
    }

    public List<CategoryFindAllResponseDto> findAllCategoriesDto() {
        List<CategoryFindAllResponseDto> categoryFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(categories -> {
            categoryFindAllResponseDtos.add(CategoriesMapper.INSTANCE.categoryFindAllResponseDtoToCategories(categories));
        });
        return categoryFindAllResponseDtos;
    }

    public CategoryFindAllResponseDto findCategoryById(Long id){
        return CategoriesMapper.INSTANCE.categoryFindAllResponseDtoToCategories(categoriesRepository.findById(id).get());
    }


}
