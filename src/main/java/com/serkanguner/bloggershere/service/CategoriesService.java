package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.dto.request.CategorySaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CategoryFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.exception.BloggerHereException;
import com.serkanguner.bloggershere.exception.ErrorType;
import com.serkanguner.bloggershere.mapper.CategoriesMapper;
import com.serkanguner.bloggershere.repository.CategoriesRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CategoriesService extends ServiceManager<Categories,Long> {
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
    }

    // Categoryleri Mapper kullanarak save eder
    public void categoriesSaveDto(CategorySaveRequestDto categorySaveRequestDto) {
        categoriesRepository.save(CategoriesMapper.INSTANCE.categoriesSaveRequestDtoToCategories(categorySaveRequestDto));
    }

    // Categoryleri Mapper kullanarak listeler
    public List<CategoryFindAllResponseDto> findAllCategoriesDto() {
        List<CategoryFindAllResponseDto> categoryFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(categories -> {
            categoryFindAllResponseDtos.add(CategoriesMapper.INSTANCE.categoryFindAllResponseDtoToCategories(categories));
        });
        return categoryFindAllResponseDtos;
    }

    /**
     * Returns
     * @param id : Category id
     * @return CategoryFindAllResponseDto
     * @throws BloggerHereException if category id is not found
     */
    public CategoryFindAllResponseDto findCategoryById(Long id){
        Optional<Categories> byId = categoriesRepository.findById(id);
        if(byId.isEmpty()){
            throw new BloggerHereException(ErrorType.CATEGORY_NOT_FOUND,"Category not found");
        }
       return CategoriesMapper.INSTANCE.categoryFindAllResponseDtoToCategories(byId.get());

    }

    /**
     * updateCategoryById take id and findById given Id and add new( name, description)
     * Returns
     * @param id : Category id
     * @param name : new category name
     * @param description : new category description
     * @return
     */
    public String updateCategoryById(Long id, String name,String description){
        Optional<Categories> byId = categoriesRepository.findById(id);
        if(byId.isEmpty()){
            throw new BloggerHereException(ErrorType.CATEGORY_NOT_FOUND,"Category not found");
        }
        Categories categories = byId.get();
        categories.setName(name);
        categories.setDescription(description);
        categoriesRepository.save(categories);
        return "Guncelleme Basarili";
    }
    // Category id ye gore catogoriyi silme islemi
    public String deleteCategoryById(Long id){
        Optional<Categories> byId = categoriesRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerHereException(ErrorType.CATEGORY_NOT_FOUND,"Category  not found");
        }
        categoriesRepository.deleteById(id);
        return "Silme Basarili";
    }

    //Categories name aranan ifadeyi iceriyorsa categeroy bilgisini doner. Bulamazsa kategori bulunamadi hatasi firlatir.
    public List<CategoryFindAllResponseDto>  findAllByNameContaining(String name){
        AtomicReference<Boolean> ok = new AtomicReference<>(false);
        List<Categories> allByNameContainingIgnoreCase = categoriesRepository.findAllByNameContainingIgnoreCase(name);
        allByNameContainingIgnoreCase.forEach(categories -> {
             ok.set(categories.getName().equalsIgnoreCase(categories.getName()));

        }
        );
        if(!ok.get()){
          throw new BloggerHereException(ErrorType.CATEGORY_NOT_FOUND,"Category is not found");
        }
        List<CategoryFindAllResponseDto> collect = allByNameContainingIgnoreCase
                .stream()
                .map(CategoriesMapper.INSTANCE::categoryFindAllResponseDtoToCategories)
                .collect(Collectors.toList());
        return collect;
    }





}
