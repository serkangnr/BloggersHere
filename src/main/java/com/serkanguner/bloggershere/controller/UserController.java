package com.serkanguner.bloggershere.controller;

import com.serkanguner.bloggershere.dto.request.UserSaveRequestDto;
import com.serkanguner.bloggershere.dto.response.UserFindAllResponseDto;
import com.serkanguner.bloggershere.exception.BloggerHereException;
import com.serkanguner.bloggershere.exception.ErrorType;
import com.serkanguner.bloggershere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.serkanguner.bloggershere.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT + USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    // User save eder kaydederken mail ve password kontrolu yapar.
    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> save(@RequestBody UserSaveRequestDto dto) {
        if (!dto.email().endsWith("@gmail.com")) {
            throw new BloggerHereException(ErrorType.WRONG_EMAIL_TYPE, "Wrong email Type");
        } else if (dto.password().length() != 8) {
            throw new BloggerHereException(ErrorType.WRONG_PASSWORD_LENGTH, "Wrong password length");
        } else {
            userService.userSaveDto(dto);
            return ResponseEntity.ok("Kayit Basarili");
        }
    }

    // User listesi doner
    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<UserFindAllResponseDto>> findAllDto() {
        ResponseEntity<List<UserFindAllResponseDto>> listResponseEntity = ResponseEntity.ok(userService.findUserDto());
        if (listResponseEntity == null) {
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        return listResponseEntity;

    }

    //Belirlenen Id ye gore user listesi doner
    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findByIdDto(@RequestParam("id") Long id) {
        ResponseEntity<UserFindAllResponseDto> dtoResponseEntity = ResponseEntity.ok(userService.findUserDtoID(id));

        return dtoResponseEntity;
    }

    // Verilen name and lastname gore user listesi doner
    @GetMapping(FINDBYNAMEANDLASTNAME)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findUserByNameAndLastName(@RequestParam(name = "name") String name, @RequestParam("lastname") String lastname) {
        ResponseEntity<UserFindAllResponseDto> dtoResponseEntity = ResponseEntity.ok(userService.findUserDto(name, lastname));
        if (dtoResponseEntity.getBody() == null) {
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        return dtoResponseEntity;
    }

    // Id'ye gore user update edilir.
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<String> updateUser(Long id, String name, String lastName, String email, String password) {
        ResponseEntity<String> responseEntity = ResponseEntity.ok(userService.updateUser(id, name, lastName, email, password));

        return responseEntity;
    }

    // Id'ye gore user silinir.'
    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<String> deleteUser(@RequestParam(name = "id") Long id) {
        ResponseEntity<String> responseEntity = ResponseEntity.ok(userService.deleteUser(id));
        if (responseEntity.getBody() == null) {
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        return responseEntity;
    }

    // User ve Post Id'ye gore post like edilir. // Ayni user ve ayni post like etmeye calistiginda like eklemez ancak silmezde
    @PostMapping("/saveLike")
    @CrossOrigin("*")
    public ResponseEntity<String> userLikePost(@RequestParam(name = "user_id") Long userId, @RequestParam(name = "post_id") Long postId) {
        userService.userLikePost(userId, postId);
        return ResponseEntity.ok("Post liked");
    }

    // User ve Post Id'ye gore post like silinir.'
    @DeleteMapping("/saveLikeDelete")
    @CrossOrigin("*")
    public ResponseEntity<String> userLikePostDelete(@RequestParam(name = "user_id") Long userId, @RequestParam(name = "post_id") Long postId) {

        userService.userLikePostDelete(userId, postId);

        return ResponseEntity.ok("Like deleted");
    }

}
