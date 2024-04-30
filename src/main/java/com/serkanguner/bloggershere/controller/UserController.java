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
        ResponseEntity<List<UserFindAllResponseDto>> listResponseEntity = ResponseEntity.ok(userService.findUserDto());
        if(listResponseEntity==null){
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND,"User not found");
        }
        return listResponseEntity;

    }
    @GetMapping(FINDBYID)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findByIdDto(@RequestParam Long id){
        ResponseEntity<UserFindAllResponseDto> dtoResponseEntity = ResponseEntity.ok(userService.findUserDtoID(id));

        return dtoResponseEntity;
    }

    @GetMapping(FINDBYNAMEANDLASTNAME)
    @CrossOrigin("*")
    public ResponseEntity<UserFindAllResponseDto> findUserByNameAndLastName(@RequestParam String name, @RequestParam String lastname){
        ResponseEntity<UserFindAllResponseDto> dtoResponseEntity = ResponseEntity.ok(userService.findUserDto(name, lastname));
        if (dtoResponseEntity.getBody()==null) {
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        return dtoResponseEntity;
    }

    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<String>updateUser(Long id,String name,String lastName,String email,String password){
        ResponseEntity<String> responseEntity = ResponseEntity.ok(userService.updateUser(id, name, lastName, email, password));

        return responseEntity;
    }

    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<String> deleteUser(Long id){
        ResponseEntity<String> responseEntity = ResponseEntity.ok(userService.deleteUser(id));
        if (responseEntity.getBody()==null) {
            throw new BloggerHereException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        return responseEntity;
    }




}
