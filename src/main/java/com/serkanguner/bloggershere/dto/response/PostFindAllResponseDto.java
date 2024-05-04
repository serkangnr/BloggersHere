package com.serkanguner.bloggershere.dto.response;

import java.time.LocalDate;
import java.util.List;
// Postlarin icerigi, user ve categorysi ile commentleri listelemek icin yapildi.
public record PostFindAllResponseDto(String title, String content, LocalDate published_at, Long user_id, Long categories_id,
                                     List<CommentSimpleDto> comments) {
}
