package com.serkanguner.bloggershere.service;

import com.serkanguner.bloggershere.entity.Post;
import com.serkanguner.bloggershere.repository.PostRepository;
import com.serkanguner.bloggershere.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class PostService extends ServiceManager<Post,Long> {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        super(postRepository);
        this.postRepository = postRepository;
    }


}
