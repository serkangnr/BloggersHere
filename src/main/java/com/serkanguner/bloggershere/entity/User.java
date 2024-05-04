package com.serkanguner.bloggershere.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 25)
    private String name;
    @Column(length = 25)
    private String lastname;
    @Email(message = "Can only use gmail") // Email check // Sadece gmail.com uzantili mail adresi kabul edilir
    private String email;
    @Column(length = 8, nullable = false)
    @Size(min = 8, max = 8, message = "Password must be 8 characters long") // 8 karakter siniri koyar
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts; // Bir user'in birden cok postu olabilir

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<Comment> comments; // Bir user'in birden cok comment'i olabilir'

    @ManyToMany
    private List<Post> likes; // Bir postu birden cok user begenebilir ve bir post birden cok begenen user'a sahip olabilir.
}
