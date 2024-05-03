package com.serkanguner.bloggershere.dto.request;

public record CommentSaveRequestDto(String content, Long userid, Long postid) {
}
