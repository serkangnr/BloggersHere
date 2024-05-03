package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.dto.request.UserSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Post;
import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.exception.BloggerHereException;
import com.serkanguner.bloggershere.exception.ErrorType;
import com.serkanguner.bloggershere.mapper.UserMapper;
import com.serkanguner.bloggershere.repository.PostRepository;
import com.serkanguner.bloggershere.repository.UserRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService extends ServiceManager<User, Long> {
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public UserService(UserRepository userRepository, PostRepository postRepository) {
        super(userRepository);
        this.userRepository = userRepository;

        this.postRepository = postRepository;
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
        AtomicBoolean equals = new AtomicBoolean(false);
        findById(id).stream().forEach(user -> {
            equals.set(user.getId().equals(id));
        });
        if (equals.get()) {
            UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(byId.get());
            return userFindAllResponseDto;
        }
        return null;
    }

    // Belirli bir kullanicinin detaylarini ad soyad ile getiren metod
    public UserFindAllResponseDto findUserDto(String name, String lastname) {

        User findByNameAndByLastname = userRepository.findByNameIgnoreCaseAndLastnameIgnoreCase(name, lastname);
        UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(findByNameAndByLastname);
        return userFindAllResponseDto;
    }

    public String updateUser(Long id, String name, String lastName, String email, String password) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        User user = byId.get();
        user.setName(name);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "Kayit Basarili";
    }

    public String deleteUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        User user = byId.get();
        userRepository.delete(user);
        return "Silme islemi Basarili";
    }

    public void userLikePost(Long userId, Long postId) {
        Optional<User> user1 = userRepository.findById(userId);
        Optional<Post> post1 = postRepository.findById(postId);

        if (user1.isPresent() && post1.isPresent()) {
            User user = user1.get();
            Post post = post1.get();


            if (!user.getLikes().contains(post)) {
                user.getLikes().add(post);
                userRepository.save(user);
            }


            if (!post.getUsers().contains(user)) {
                post.getUsers().add(user);
                postRepository.save(post);
            }
        } else {
            throw new BloggerHereException(ErrorType.USER_OR_POST_NOT_FOUND, "User or Post not found");
        }


    }
    public String userLikePostDelete(Long userId, Long postId) {

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);


        if (userOptional.isPresent() && postOptional.isPresent()) {
            User user = userOptional.get();
            Post post = postOptional.get();


            post.getUsers().remove(user);
            postRepository.save(post);


            user.getLikes().remove(post);
            userRepository.save(user);

            return "Silme Başarılı";
        } else {
            throw new BloggerHereException(ErrorType.USER_OR_POST_NOT_FOUND, "Kullanıcı veya gönderi bulunamadı");
        }
    }

}





