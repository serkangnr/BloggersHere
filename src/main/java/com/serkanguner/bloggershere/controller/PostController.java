package com.serkanguner.bloggershere.controller;


import static com.serkanguner.bloggershere.constant.EndPoints.*;

import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.PostFindAllResponseDto;
import com.serkanguner.bloggershere.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ROOT+POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


//    @PostMapping(SAVE) // User_id' ler eklenmiyor
//    @CrossOrigin("*")
//    public ResponseEntity<String> savePost(@RequestBody PostSaveRequestDto postSaveRequestDto){
//        postService.savePost(postSaveRequestDto);
//        return ResponseEntity.ok("Post saved successfully");
//    }
    @PostMapping("/savepost") // User_id' ler ekleniyor
    @CrossOrigin("*")
    public ResponseEntity<String> savePost(@RequestBody PostSaveRequestDto postSaveRequestDto){
        postService.savePost(postSaveRequestDto);
        return ResponseEntity.ok("Post saved successfully");
    }

    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllDto(){
        return ResponseEntity.ok(postService.findPostDto());
    }

    @GetMapping(FINDBYPOSTID)
    @CrossOrigin("*")
    public ResponseEntity<PostFindAllResponseDto> findByIdDto(@RequestParam Long id){
        return ResponseEntity.ok(postService.findPostDtoID(id));
    }
    @GetMapping(FINDBYUSERID)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllPostByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(postService.findAllPostByUserId(userId));
    }

    @GetMapping(FINDBYCATEGORYID)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllPostByCategoryId(@RequestParam Long id){
        return ResponseEntity.ok(postService.findAllPostByCategoriesId(id));
    }


}


