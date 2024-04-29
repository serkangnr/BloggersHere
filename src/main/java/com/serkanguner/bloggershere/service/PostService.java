package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.PostFindAllResponseDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.entity.Post;
import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.mapper.PostMapper;
import com.serkanguner.bloggershere.mapper.UserMapper;
import com.serkanguner.bloggershere.repository.PostRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService extends ServiceManager<Post,Long> {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        super(postRepository);
        this.postRepository = postRepository;
    }

//    public void savePost(PostSaveRequestDto postSaveRequestDto){
//        postRepository.save(PostMapper.INSTANCE.postSaveRequestDtoToPost(postSaveRequestDto));
//    }

    public List<PostFindAllResponseDto> findPostDto() {
        List<PostFindAllResponseDto> postFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(post -> {
            postFindAllResponseDtos.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        });
        return postFindAllResponseDtos;
    }

    public String savePost(PostSaveRequestDto postSaveRequestDto){
        User user = User.builder()
                .id(postSaveRequestDto.user_id())
                .build();
        Categories categories = Categories.builder()
                .id(postSaveRequestDto.categories_id())
                .build();
        Post post = Post.builder()
                .user(user)
                .categories(categories)
                .title(postSaveRequestDto.title())
                .content(postSaveRequestDto.content())
                .build();
        user.setPosts(List.of(post));
        postRepository.save(post);
        return "Kayit Basarili";
    }
    // post id'si ile bulma
    public PostFindAllResponseDto findPostDtoID(Long id) {
        Optional<Post> byId = postRepository.findById(id);
        PostFindAllResponseDto postFindAllResponseDto = PostMapper.INSTANCE.postToPostFindAllResponseDto(byId.get());
        return postFindAllResponseDto;
    }

    // postu user id ile bulma
    public List<PostFindAllResponseDto> findAllPostByUserId(Long userId) {
        return postRepository.findAllPostByUserId(userId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
    }

    public List<PostFindAllResponseDto> findAllPostByCategoriesId(Long categoriesId) {
        return postRepository.findAllPostByCategoriesId(categoriesId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
    }















}
