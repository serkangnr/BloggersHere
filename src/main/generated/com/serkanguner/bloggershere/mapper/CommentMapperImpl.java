package com.serkanguner.bloggershere.mapper;

import com.serkanguner.bloggershere.dto.request.CommentSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CommentFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Comment;
import com.serkanguner.bloggershere.entity.Post;
import com.serkanguner.bloggershere.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T14:22:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentFindAllResponseDto commentToCommentFindAllResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Long user_id = null;
        Long post_id = null;
        String content = null;

        user_id = commentUserId( comment );
        post_id = commentPostId( comment );
        content = comment.getContent();

        CommentFindAllResponseDto commentFindAllResponseDto = new CommentFindAllResponseDto( content, user_id, post_id );

        return commentFindAllResponseDto;
    }

    @Override
    public Comment commentSaveRequestDtoToComment(CommentSaveRequestDto commentSaveRequestDto) {
        if ( commentSaveRequestDto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.content( commentSaveRequestDto.content() );

        return comment.build();
    }

    private Long commentUserId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentPostId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Post post = comment.getPost();
        if ( post == null ) {
            return null;
        }
        Long id = post.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
