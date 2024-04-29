package com.serkanguner.bloggershere.exception;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Bu sinif tum controller siniflari icin merkezi bir sekilde hata yonetimmi saglayacaktir.
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) // RuntimeException Hata yakalayici bir metod oldugunu belirtmek icin.
    public ResponseEntity<String> handleException(){
        return ResponseEntity.badRequest().body("Uygulamada Runtime Exception Olustu...........");
    }


    @ExceptionHandler(BloggerHereException.class)
    public ResponseEntity<ErrorMessage> handleDemoException(BloggerHereException bloggerHereException){
        ErrorType errorType = bloggerHereException.getErrorType();

        return new ResponseEntity(createErrorMessage(bloggerHereException),errorType.getHttpStatus());
    }

    private ErrorMessage createErrorMessage(BloggerHereException bloggerHereException) {
       return ErrorMessage.builder()
               .code(bloggerHereException.getErrorType().getCode())
               .message(bloggerHereException.getMessage())
               .build();
    }



}
