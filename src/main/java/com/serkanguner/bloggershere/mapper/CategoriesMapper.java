package com.serkanguner.bloggershere.mapper;

import com.serkanguner.bloggershere.dto.request.CategorySaveRequestDto;
import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CategoryFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriesMapper {
    CategoriesMapper INSTANCE = Mappers.getMapper(CategoriesMapper.class);

    Categories categoriesSaveRequestDtoToCategories(CategorySaveRequestDto categorySaveRequestDto);
    CategoryFindAllResponseDto categoryFindAllResponseDtoToCategories(Categories categories);
}
