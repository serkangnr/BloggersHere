package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.PostFindAllResponseDto;
import com.serkanguner.bloggershere.entity.Categories;
import com.serkanguner.bloggershere.entity.Post;
import com.serkanguner.bloggershere.entity.User;
import com.serkanguner.bloggershere.exception.BloggerHereException;
import com.serkanguner.bloggershere.exception.ErrorType;
import com.serkanguner.bloggershere.mapper.PostMapper;
import com.serkanguner.bloggershere.repository.PostRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
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
        if (byId.isEmpty()){
            throw new BloggerHereException(ErrorType.POST_NOT_FOUND,"Post not found");
        }
        PostFindAllResponseDto postFindAllResponseDto = PostMapper.INSTANCE.postToPostFindAllResponseDto(byId.get());
        return postFindAllResponseDto;
    }

    // postu user id ile bulma
    public List<PostFindAllResponseDto> findAllPostByUserId(Long userId) {
        AtomicBoolean equals = new AtomicBoolean(false);
        List<PostFindAllResponseDto> postFindAllResponseDtos = postRepository.findAllPostByUserId(userId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
        postFindAllResponseDtos.stream().forEach(postFindAllResponseDto -> {
             equals.set(postFindAllResponseDtos.get(0).user_id().equals(userId));
        });
        if (!equals.get()){
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND,"User not found");
        }
        return postFindAllResponseDtos;
    }

    public List<PostFindAllResponseDto> findAllPostByCategoriesId(Long categoriesId) {
        AtomicBoolean equals = new AtomicBoolean(false);
        List<PostFindAllResponseDto> postFindAllResponseDtos = postRepository.findAllPostByCategoriesId(categoriesId).stream().map(PostMapper.INSTANCE::postToPostFindAllResponseDto).collect(Collectors.toList());
        postFindAllResponseDtos.stream().forEach(postFindAllResponseDto -> {
            equals.set(postFindAllResponseDtos.get(0).categories_id().equals(categoriesId));
        });
        if (!equals.get()){
            throw new BloggerHereException(ErrorType.CATEGORY_NOT_FOUND,"Categories not found");
        }
        return postFindAllResponseDtos;
    }

    public String updatePost(Long id, String title, String content){
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerHereException(ErrorType.POST_NOT_FOUND,"Post not found");
        }
        Post post = byId.get();
        post.setTitle(title);
        post.setContent(content);
        post.setPublished_at(LocalDate.now());
        postRepository.save(post);
        return "Guncelleme Basarili";
    }

    public String deletePost(Long id){
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isEmpty()){
            throw new BloggerHereException(ErrorType.POST_NOT_FOUND,"Post not found");
        }
        Post post = byId.get();
        postRepository.delete(post);
        return "Silme Basarili";
    }

    public List<PostFindAllResponseDto> findAllByContentContainingIgnoreCase(String content){
        AtomicBoolean equals = new AtomicBoolean(false);
        List<PostFindAllResponseDto> postFindAllResponseDtos = postRepository
                .findAllByContentContainingIgnoreCase(content)
                .stream()
                .map(PostMapper.INSTANCE::postToPostFindAllResponseDto)
                .collect(Collectors.toList());
        postFindAllResponseDtos.stream().forEach(postFindAllResponseDto -> {
             equals.set(postFindAllResponseDtos.get(0).content().contains(content));
        });
        if (!equals.get()){
            throw new BloggerHereException(ErrorType.POST_NOT_FOUND,"Post not found");
        }
        return postFindAllResponseDtos;
    }

    public List<PostFindAllResponseDto> findAllPostByCategoriesNameContainingIgnoreCase(String name) {
        List<PostFindAllResponseDto> postFindAllResponseDtos = new ArrayList<>();
        AtomicBoolean ok = new AtomicBoolean(false);

        List<Post> allPostByCategoriesNameContainingIgnoreCase = postRepository.findAllPostByCategoriesNameContainingIgnoreCase(name);
        if (allPostByCategoriesNameContainingIgnoreCase.isEmpty()){
            throw new BloggerHereException(ErrorType.CATEGORY_NOT_FOUND,"Categories not found");
        }
        postRepository.findAllPostByCategoriesNameContainingIgnoreCase(name).forEach(post ->{
            postFindAllResponseDtos.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        });

        return postFindAllResponseDtos;
    }

    public List<PostFindAllResponseDto> findAllPostOrderByPublished(){
        List<PostFindAllResponseDto> postFindAllResponseDtos = new ArrayList<>();

        postRepository.findAllPublished().forEach(post -> {
            postFindAllResponseDtos.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        });
        return postFindAllResponseDtos;
    }















}
