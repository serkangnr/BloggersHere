package com.serkanguner.bloggershere.dto.request;
// Bir postun kategorisi olmali ve bir kullaniciya ait olmalidir. Post olusmasi icin iceriginin de olmasi gereklidir.
public record PostSaveRequestDto(Long user_id,Long categories_id, String title, String content) {
}
