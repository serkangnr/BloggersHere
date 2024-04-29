package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.dto.request.UserSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.mapper.UserMapper;
import com.serkanguner.bloggershere.repository.UserRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User, Long> {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public void userSaveDto(UserSaveRequestDto userSaveRequestDto) {
        userRepository.save(UserMapper.INSTANCE.userSaveRequestDtoToUser(userSaveRequestDto));
    }

    public List<UserFindAllResponseDto> findUserDto() {
        List<UserFindAllResponseDto> userFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(user -> {
            userFindAllResponseDtos.add(UserMapper.INSTANCE.userToUserFindAllResponseDto(user));
        });
        return userFindAllResponseDtos;
    }
    // Belirli bir kullanicinin detaylarini id ile getiren metod

    public UserFindAllResponseDto findUserDtoID(Long id) {
        Optional<User> byId = userRepository.findById(id);
        UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(byId.get());
        return userFindAllResponseDto;
    }

    // Belirli bir kullanicinin detaylarini ad soyad ile getiren metod
    public UserFindAllResponseDto findUserDto(String name, String lastname) {

        User findByNameAndByLastname = userRepository.findByNameIgnoreCaseAndLastnameIgnoreCase(name, lastname);
        UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(findByNameAndByLastname);
        return userFindAllResponseDto;
    }





}
