package com.serkanguner.bloggershere.mapper;

import com.serkanguner.bloggershere.dto.request.UserSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userSaveRequestDtoToUser(UserSaveRequestDto userSaveRequestDto);

    UserFindAllResponseDto userToUserFindAllResponseDto(User user);
}
