package com.serkanguner.bloggershere.dto.request;
// Comment olustururken bir contente ve bir user ve bir posta ihtiyac vardir.
public record CommentSaveRequestDto(String content, Long userid, Long postid) {
}
