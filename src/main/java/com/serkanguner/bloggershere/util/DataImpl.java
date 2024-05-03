package com.serkanguner.bloggershere.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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



    public class JSONReader {
        public static void main(String[] args) {
            // JSON dosyasının yolunu belirtin
            String dosyaYolu = "path/to/your/file.json";

            try {
                // Gson nesnesini oluşturun
                Gson gson = new Gson();

                // JsonParser nesnesini oluşturun
                JsonParser parser = new JsonParser();

                // FileReader kullanarak JSON dosyasını okuyun
                JsonElement jsonElement = parser.parse(new FileReader(dosyaYolu));

                // JSON verisini istediğiniz sınıfa dönüştürün (varsayılan olarak JsonObject kullanabilirsiniz)
                // Örneğin, eğer JSON dosyasının içeriği bir User sınıfına uygunsa:
                // User user = gson.fromJson(jsonElement, User.class);

                // JSON verisini işlemek için gerekli işlemleri yapın
                // Örneğin, JSON verisini yazdırın
                System.out.println(jsonElement);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






    @Override
    public void run (ApplicationArguments args) throws Exception {
       //  veriEkle();
    }

}