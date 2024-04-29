package com.serkanguner.bloggershere.exception;

import lombok.Getter;


@Getter
public class BloggerHereException extends RuntimeException {
    private ErrorType errorType;

    public BloggerHereException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BloggerHereException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }


}
