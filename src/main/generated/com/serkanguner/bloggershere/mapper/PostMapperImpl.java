package com.serkanguner.bloggershere.mapper;

import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CommentSimpleDto;
import com.serkanguner.bloggershere.dto.response.PostFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.entity.Comment;
import com.serkanguner.bloggershere.entity.Post;
import com.serkanguner.bloggershere.entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T13:27:18+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostFindAllResponseDto postToPostFindAllResponseDto(Post post) {
        if ( post == null ) {
            return null;
        }

        Long user_id = null;
        Long categories_id = null;
        String title = null;
        String content = null;
        LocalDate published_at = null;
        List<CommentSimpleDto> comments = null;

        user_id = postUserId( post );
        categories_id = postCategoriesId( post );
        title = post.getTitle();
        content = post.getContent();
        published_at = post.getPublished_at();
        comments = commentListToCommentSimpleDtoList( post.getComments() );

        PostFindAllResponseDto postFindAllResponseDto = new PostFindAllResponseDto( title, content, published_at, user_id, categories_id, comments );

        return postFindAllResponseDto;
    }

    @Override
    public Post postSaveRequestDtoToPost(PostSaveRequestDto postSaveRequestDto) {
        if ( postSaveRequestDto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.title( postSaveRequestDto.title() );
        post.content( postSaveRequestDto.content() );

        return post.build();
    }

    private Long postUserId(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long postCategoriesId(Post post) {
        if ( post == null ) {
            return null;
        }
        Categories categories = post.getCategories();
        if ( categories == null ) {
            return null;
        }
        Long id = categories.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected CommentSimpleDto commentToCommentSimpleDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        String content = null;

        content = comment.getContent();

        CommentSimpleDto commentSimpleDto = new CommentSimpleDto( content );

        return commentSimpleDto;
    }

    protected List<CommentSimpleDto> commentListToCommentSimpleDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentSimpleDto> list1 = new ArrayList<CommentSimpleDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToCommentSimpleDto( comment ) );
        }

        return list1;
    }
}
