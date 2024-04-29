package com.serkanguner.bloggershere.dto.request;

public record PostSaveRequestDto(Long user_id,Long categories_id, String title, String content) {
}
