package com.lld.practise.systemDesign.urlShortner.service;

import com.lld.practise.systemDesign.urlShortner.entity.UrlDetails;
import com.lld.practise.systemDesign.urlShortner.repository.UrlDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectUrlService {
    @Autowired
    private UrlDetailsRepository urlDetailsRepository;
    public String getLongUrl(String shortUrl) {
        Optional<UrlDetails> urlDetails=urlDetailsRepository.findByShortUrl(shortUrl);
        if(urlDetails.isEmpty())
            throw new RuntimeException("Url not found exception");

        return urlDetails.get().getLongUrl();

    }
}
