package com.lld.practise.systemDesign.urlShortner.exception;

public class UrlAlreadyExistException extends RuntimeException{
    public UrlAlreadyExistException(String message, String url){
        super(message);
    }
}
