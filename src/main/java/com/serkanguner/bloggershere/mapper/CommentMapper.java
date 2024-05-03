package com.serkanguner.bloggershere.mapper;

import com.serkanguner.bloggershere.dto.request.CommentSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CommentFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "userid", source = "comment.user.id")
    @Mapping(target = "postid", source = "comment.post.id")

    CommentFindAllResponseDto commentToCommentFindAllResponseDto (Comment comment);
    @InheritInverseConfiguration
    Comment commentSaveRequestDtoToComment(CommentSaveRequestDto commentSaveRequestDto);





}
