package com.serkanguner.bloggershere.repository;
import com.serkanguner.bloggershere.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // ID ile post arama
    Optional<Post> findById(Long id);

    //User id ile post ara
    List<Post> findAllPostByUserId(Long userId);

    //Categories id ile post arama
    List<Post> findAllPostByCategoriesId(Long categoriesId);

    //Yazıları içeriğine göre arama
    List<Post> findAllByContentContainingIgnoreCase(String content);


    //GET /api/posts?category={kategori}: Belirli bir kategoriye ait yazıları getir.

    List<Post> findAllPostByCategoriesNameContainingIgnoreCase(String categoriesName);

    //Yazıları yayın tarihine göre sıralama seçeneği (published_at parametresi ile).
    @Query("SELECT p FROM Post p ORDER BY p.published_at DESC")
    List<Post> findAllPublished();










}
