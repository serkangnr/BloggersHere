package com.serkanguner.bloggershere.dto.request;

import com.serkanguner.bloggershere.dto.response.UserPostFindAllResponseDto;

import java.util.List;

public record CategorySaveRequestDto(String name, String description) {
}
