package com.serkanguner.bloggershere.util;

import com.google.gson.Gson;
import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.service.CategoriesService;
import com.serkanguner.bloggershere.service.PostService;
import com.serkanguner.bloggershere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DataLoad implements ApplicationRunner {
    private final UserService userService;
    private final CategoriesService categoriesService;
    private final PostService postService;


// Database ekle

    public void loadUser() {
        try {
            // Dosyayı oku
            FileReader reader = new FileReader("src/main/resources/user.json");

            // JSON verilerini ayrıştır
            Gson gson = new Gson();
            User[] users = gson.fromJson(reader, User[].class);

            // Verileri işle
            for (User user : users) {
                String name = user.getName();
                String lastname = user.getLastname();
                String email = user.getEmail();
                String password = user.getPassword();

                User user1 = User.builder()
                        .name(name)
                        .lastname(lastname)
                        .email(email)
                        .password(password)
                        .build();
                userService.save(user1);

            }

            // Dosyayı kapat
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadCategories() {
        try {
            // Dosyayı oku
            FileReader reader = new FileReader("src/main/resources/category.json");

            // JSON verilerini ayrıştır
            Gson gson = new Gson();
            Categories[] categories = gson.fromJson(reader, Categories[].class);

            // Verileri işle
            for (Categories category : categories) {
                String name = category.getName();
                String description = category.getDescription();

                Categories categories1 = Categories.builder()
                        .name(name)
                        .description(description)
                        .build();
                categoriesService.save(categories1);

            }

            // Dosyayı kapat
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadPost() {
        try {
            // Dosyayı oku
            FileReader reader = new FileReader("src/main/resources/post.json");

            // JSON verilerini ayrıştır
            Gson gson = new Gson();
            PostSaveRequestDto[] posts = gson.fromJson(reader, PostSaveRequestDto[].class);

            // Verileri işle
            for (PostSaveRequestDto post : posts) {
                Long userId = post.user_id(); // Gelen verilerde user_id oldugu icin post classini kullanilamaz bu yuzden dto ile cekildi.
                Long categoriesId = post.categories_id();
                String title = post.title();
                String content = post.content();

                PostSaveRequestDto postSaveRequestDto = new  PostSaveRequestDto(userId,categoriesId,title,content);
                postService.savePost(postSaveRequestDto);


            }

            // Dosyayı kapat
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //loadUser();
        //loadCategories();
        //loadPost();

    }
}
