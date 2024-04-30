package com.serkanguner.bloggershere.controller;

import static com.serkanguner.bloggershere.constant.EndPoints.*;

import com.serkanguner.bloggershere.dto.request.CategorySaveRequestDto;
import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CategoryFindAllResponseDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ROOT + CATEGORIES)
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;


    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> saveCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto) {
        categoriesService.categoriesSaveDto(categorySaveRequestDto);
        return ResponseEntity.ok("Category saved successfully");
    }

    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAllCategoriesDto() {
        return ResponseEntity.ok(categoriesService.findAllCategoriesDto());
    }

    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<CategoryFindAllResponseDto> findAllCategoriesById(@RequestParam Long id) {
        return ResponseEntity.ok(categoriesService.findCategoryById(id));
    }

    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<String> updateCategory(Long id, String name, String description) {
        categoriesService.updateCategoryById(id, name, description);
        return ResponseEntity.ok("Category updated successfully");
    }

    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<String> deleteCategory(Long id) {
        categoriesService.deleteCategoryById(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @GetMapping(FINDALLBYNAMECONTAINING)
    @CrossOrigin("*")
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAllCategoriesByNameContaining(@RequestParam String name){
        return ResponseEntity.ok(categoriesService.findAllByNameContaining(name));
    }


}
