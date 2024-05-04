package com.serkanguner.bloggershere.dto.response;

import java.util.List;
// Kategori listelenirken kategoriye ait postlarda eklendi.
public record CategoryFindAllResponseDto(String name, String description, List<UserPostFindAllResponseDto>posts) {
}
