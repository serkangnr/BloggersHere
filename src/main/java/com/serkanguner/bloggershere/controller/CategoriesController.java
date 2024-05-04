package com.serkanguner.bloggershere.controller;

import com.serkanguner.bloggershere.dto.request.CategorySaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CategoryFindAllResponseDto;
import com.serkanguner.bloggershere.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.serkanguner.bloggershere.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT + CATEGORIES)
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;


    // Category nesnesini save eder.
    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> saveCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto) {
        categoriesService.categoriesSaveDto(categorySaveRequestDto);
        return ResponseEntity.ok("Category saved successfully");
    }

    // Category Listesi doner.
    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAllCategoriesDto() {
        return ResponseEntity.ok(categoriesService.findAllCategoriesDto());
    }

    // Category Id'sine gore Category doner.'
    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<CategoryFindAllResponseDto> findAllCategoriesById(@RequestParam Long id) {
        return ResponseEntity.ok(categoriesService.findCategoryById(id));
    }

    // Category Id'sine' gore category gunceller.
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<String> updateCategory(Long id, String name, String description) {
        categoriesService.updateCategoryById(id, name, description);
        return ResponseEntity.ok("Category updated successfully");
    }

    // Category Id'sine' gore category siler.
    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<String> deleteCategory(Long id) {
        categoriesService.deleteCategoryById(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    // Category ismine gore Category doner.
    @GetMapping(FINDALLBYNAMECONTAINING)
    @CrossOrigin("*")
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAllCategoriesByNameContaining(@RequestParam String name){
        return ResponseEntity.ok(categoriesService.findAllByNameContaining(name));
    }


}
