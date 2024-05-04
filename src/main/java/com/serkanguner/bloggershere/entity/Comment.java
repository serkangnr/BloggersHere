package com.serkanguner.bloggershere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 2048)
    private String content;
    @Builder.Default
    private LocalDateTime date=LocalDateTime.now(); // Comment'in atilma zamaninini yakalar.

    @ManyToOne
    private Post post;
    //Comment tarafi Many olan taraf oldugu icin ownerdir.
    @ManyToOne
    private User user;


}
