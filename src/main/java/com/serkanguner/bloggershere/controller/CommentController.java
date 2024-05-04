package com.serkanguner.bloggershere.controller;

import com.serkanguner.bloggershere.dto.request.CommentSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.CommentFindAllResponseDto;
import com.serkanguner.bloggershere.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.serkanguner.bloggershere.constant.EndPoints.COMMENT;
import static com.serkanguner.bloggershere.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT + COMMENT)
@RequiredArgsConstructor
// User ve postlara commentler eklendi.
public class CommentController {
    private final CommentService commentService;

    //Comment kaydeder.(Not Mapper)
    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> save(@RequestBody CommentSaveRequestDto dto) {
        commentService.commentSaveDto(dto);
        return ResponseEntity.ok("Kayit Basarili");
    }

    //Comment kaydeder.(Mapper) // Id'ler'i cekemiyor.
    @PostMapping("/SaveOtherWay")
    @CrossOrigin("*")
    public ResponseEntity<String> saveOtherWay(@RequestBody CommentSaveRequestDto dto) {
        commentService.commentSaveDtoMapper(dto);
        return ResponseEntity.ok("Kayit Basarili");
    }

    // Comment listesini doner
    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<CommentFindAllResponseDto>> findAllDto(){
        return ResponseEntity.ok(commentService.findCommentDto());
    }
}
