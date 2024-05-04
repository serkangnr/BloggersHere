package com.serkanguner.bloggershere.dto.response;

import java.util.List;
//User'in bilgileri attigi postlar ve attigi yorumlar listelendi.
public record UserFindAllResponseDto(String name, String lastname, List<UserPostFindAllResponseDto> posts, List<CommentSimpleDto> comments) {
}
