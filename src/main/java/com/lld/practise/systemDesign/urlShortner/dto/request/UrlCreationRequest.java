package com.lld.practise.systemDesign.urlShortner.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class UrlCreationRequest {

    @NotBlank(message = "Long url cannot be blank")
    @URL(message = "URL format is not valid")
    private String longUrl;

}