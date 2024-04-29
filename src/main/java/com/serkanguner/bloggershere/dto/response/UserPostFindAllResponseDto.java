package com.serkanguner.bloggershere.dto.response;

import java.time.LocalDate;

public record UserPostFindAllResponseDto(String title, String content, LocalDate published_at) {
}
