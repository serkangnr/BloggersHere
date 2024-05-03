package com.serkanguner.bloggershere.repository;

import com.serkanguner.bloggershere.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // ID ile kullanici arama
    Optional<User> findById(Long id);
    // Isim soyisim ile kullanici arama
    User findByNameIgnoreCaseAndLastnameIgnoreCase(String name, String lastname);



    //like alan bir postu user_id ve post_id girerek kaldirma
    @Query(value = "DELETE FROM tbl_user_likes WHERE users_id = ?1 AND likes_id = ?2", nativeQuery = true)
    void userLikePostDelete(Long userId,  Long postId);



}
