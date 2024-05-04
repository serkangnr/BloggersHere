package com.serkanguner.bloggershere.dto.response;
// Comment bir posta ve bir user'a ait olmali
public record CommentFindAllResponseDto(String content, Long userid, Long postid) {
}
