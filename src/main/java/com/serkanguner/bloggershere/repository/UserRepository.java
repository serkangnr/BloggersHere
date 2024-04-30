package com.serkanguner.bloggershere.repository;
import com.serkanguner.bloggershere.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // ID ile kullanici arama
    Optional<User> findById(Long id);
    // Isim soyisim ile kullanici arama
    User findByNameIgnoreCaseAndLastnameIgnoreCase(String name, String lastname);

}
