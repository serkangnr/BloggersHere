package com.serkanguner.bloggershere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 50)
    private String title;
    @Column(length = 2048)
    private String content;
    @CreationTimestamp
    LocalDate published_at;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user; //Birden fazla postun bir user'i olabilir.
    @ManyToOne
    @JoinColumn(name = "categories_id")
    Categories categories; // Birden fazla post bir kategoriye ait olabilir.

    @OneToMany(mappedBy ="post")
    private List<Comment> comments; // bir postun birden cok commenti olabilir

    @ManyToMany(mappedBy = "likes")
    private List<User> users; // bir postun birden cok begenisi olabilir ve bir user birden cok postu begenebilir
}
