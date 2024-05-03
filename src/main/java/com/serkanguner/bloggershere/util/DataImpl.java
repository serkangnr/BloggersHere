package com.serkanguner.bloggershere.util;

import com.serkanguner.bloggershere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DataImpl implements ApplicationRunner {
    private final UserService userService;

    public void veriEkle() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Java14Ultimate\\BloggersHere\\src\\main\\java\\com\\serkanguner\\data\\user.txt"))) {
            String line ;
            while ((line = bufferedReader.readLine()) != null){
                String[] root = line.split(",");
                for (int i = 0; i < root.length; i++) {
                    String name = root[0];
//                    String lastname = root[1];
//                    String email = root[2];
//                    String password = root[3];
//                    User user = User.builder()
//                            .name(name)
//                            .lastname(lastname)
//                            .email(email)
//                            .password(password)
//                            .build();
//
//                    userService.save(user);
                    System.out.println(name);
                }



            };
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





    @Override
    public void run (ApplicationArguments args) throws Exception {
         veriEkle();
    }

}