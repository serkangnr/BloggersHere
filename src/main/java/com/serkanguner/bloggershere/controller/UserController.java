package com.serkanguner.bloggershere.controller;

import static com.serkanguner.bloggershere.constant.EndPoints.*;

import com.serkanguner.bloggershere.dto.request.UserSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.exception.BloggerHereException;
import com.serkanguner.bloggershere.exception.ErrorType;
import com.serkanguner.bloggershere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ROOT+USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> save(@RequestBody UserSaveRequestDto dto){
        if (!dto.email().endsWith("@gmail.com")) {
            throw new BloggerHereException(ErrorType.WRONG_EMAIL_TYPE,"Wrong email Type");
        } else if (dto.password().length() != 8) {
            throw new BloggerHereException(ErrorType.WRONG_PASSWORD_LENGTH,"Wrong password length");
        } else {
            userService.userSaveDto(dto);
            return ResponseEntity.ok("Kayit Basarili");
        }
    }

    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<UserFindAllResponseDto>> findAllDto(){
        return ResponseEntity.ok(userService.findUserDto());
    }
    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findByIdDto(@RequestParam Long id){
        return ResponseEntity.ok(userService.findUserDtoID(id));
    }

    @GetMapping(FINDBYNAMEANDLASTNAME)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findUserByNameAndLastName(@RequestParam String name, @RequestParam String lastname){
        return ResponseEntity.ok(userService.findUserDto(name, lastname));
    }



}
