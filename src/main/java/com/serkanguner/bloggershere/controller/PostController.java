package com.serkanguner.bloggershere.controller;


import com.serkanguner.bloggershere.dto.request.PostSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.PostFindAllResponseDto;
import com.serkanguner.bloggershere.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.serkanguner.bloggershere.constant.EndPoints.*;

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
    // Postlari ekler , userId' ler eklenebiliyor.
    @PostMapping("/savepost") // User_id' ler ekleniyor
    @CrossOrigin("*")
    public ResponseEntity<String> savePost(@RequestBody PostSaveRequestDto postSaveRequestDto){
        postService.savePost(postSaveRequestDto);
        return ResponseEntity.ok("Post saved successfully");
    }

    // post listesi doner
    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllDto(){
        return ResponseEntity.ok(postService.findPostDto());
    }

    //  id'ye gore post arar.
    @GetMapping(FINDBYPOSTID)
    @CrossOrigin("*")
    public ResponseEntity<PostFindAllResponseDto> findByIdDto(@RequestParam Long id){
        return ResponseEntity.ok(postService.findPostDtoID(id));
    }
    //  user id' ye gore post arar.'
    @GetMapping(FINDBYUSERID)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllPostByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(postService.findAllPostByUserId(userId));
    }

    //  category id' ye gore post arar.'
    @GetMapping(FINDBYCATEGORYID)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllPostByCategoryId(@RequestParam Long id){
        return ResponseEntity.ok(postService.findAllPostByCategoriesId(id));
    }

    // Id si verilen postu degistirir.
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<String> updatePost(Long id, String title, String content){
        postService.updatePost(id, title, content);
        return ResponseEntity.ok("Post updated successfully");
    }

    // Id si verilen postu siler.
    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<String> deletePost(Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    // Content girilen girdiyi iceriyorsa post listesi doner
    @GetMapping(FINDALLBYCONTENTCONTAINING)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllByContentContainingIgnoreCase(String content){
        return ResponseEntity.ok(postService.findAllByContentContainingIgnoreCase(content));
    }

    // Category name girilen girdiyi iceriyorsa post listesi doner
    @GetMapping(FINDALLBYCATEGORYNAME)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllByCategoriesName(String name){
        return ResponseEntity.ok(postService.findAllPostByCategoriesNameContainingIgnoreCase(name));
    }

    // Yayinlanma tarihine gore postlari siralar.
    @GetMapping(FINDALLPOSTORDERBYPUBLISHEDDATE)
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponseDto>> findAllPostOrderByPublished(){
        return ResponseEntity.ok(postService.findAllPostOrderByPublished());
    }


}


