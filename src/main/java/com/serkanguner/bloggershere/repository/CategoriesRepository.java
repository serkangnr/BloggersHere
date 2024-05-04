package com.serkanguner.bloggershere.repository;
import com.serkanguner.bloggershere.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    // Categories id ile arama
    Optional<Categories> findById(Long id);


    // Categories name ile arama
    List<Categories> findAllByNameContainingIgnoreCase(String name);
}
