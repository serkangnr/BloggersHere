package com.serkanguner.bloggershere.dto.response;

import java.time.LocalDate;
//  User'in postlari listelemek icin ek olarak olusturuldu
public record UserPostFindAllResponseDto(String title, String content, LocalDate published_at) {
}
