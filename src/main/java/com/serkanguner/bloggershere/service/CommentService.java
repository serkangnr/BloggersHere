package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.dto.request.CommentSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CommentFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Comment;
import com.serkanguner.bloggershere.entity.Post;
import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.mapper.CommentMapper;
import com.serkanguner.bloggershere.repository.CommentRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService extends ServiceManager<Comment,Long> {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;


    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public void commentSaveDtoMapper(CommentSaveRequestDto dto){
        commentRepository.save(CommentMapper.INSTANCE.commentSaveRequestDtoToComment(dto));

    }

    public String commentSaveDto(CommentSaveRequestDto dto) {
        User user = User.builder()
                .id(dto.userid())
                .build();

        Post post = Post.builder()
                .id(dto.postid())
                .build();

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(dto.content())
                .build();
        //user.setComments(List.of(comment));
        commentRepository.save(comment);
        return "Kayit Basarili";
    }

    public List<CommentFindAllResponseDto> findCommentDto() {
        List<CommentFindAllResponseDto> commentFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(comment -> {
            commentFindAllResponseDtos.add(CommentMapper.INSTANCE.commentToCommentFindAllResponseDto(comment));
        });
        return commentFindAllResponseDtos;
    }


}
