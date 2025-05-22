package com.lld.practise.systemDesign.urlShortner.service;

import com.lld.practise.systemDesign.urlShortner.dto.request.UrlCreationRequest;
import com.lld.practise.systemDesign.urlShortner.dto.response.UrlCreationResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CreateShortUrlService {
    public UrlCreationResponse shortenUrl(@Valid UrlCreationRequest urlCreationRequest) {
        return new UrlCreationResponse();
    }
}
