package com.serkanguner.bloggershere.dto.response;

import java.util.List;

public record UserFindAllResponseDto(String name, String lastname, List<UserPostFindAllResponseDto> posts, List<CommentSimpleDto> comments) {
}
