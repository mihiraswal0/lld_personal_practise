package com.lld.practise.systemDesign.urlShortner.service;

import com.lld.practise.systemDesign.urlShortner.dto.request.UrlCreationRequest;
import com.lld.practise.systemDesign.urlShortner.dto.response.UrlCreationResponse;
import com.lld.practise.systemDesign.urlShortner.entity.AvailableId;
import com.lld.practise.systemDesign.urlShortner.entity.UrlDetails;
import com.lld.practise.systemDesign.urlShortner.enums.AvailabilityStatus;
import com.lld.practise.systemDesign.urlShortner.exception.UrlAlreadyExistException;
import com.lld.practise.systemDesign.urlShortner.repository.AvailableIdRepository;
import com.lld.practise.systemDesign.urlShortner.repository.UrlDetailsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.lld.practise.systemDesign.urlShortner.util.Constants.REDIS_QUEUE_NAME;

@Service
public class CreateShortUrlService {
    @Autowired
    private UrlDetailsRepository urlDetailsRepository;
    @Autowired
    private AvailableIdRepository availableIdRepository;
    @Autowired
    private RedisTemplate<String,String > redisTemplate;
    public UrlDetails shortenUrl(String url) {
        Optional<UrlDetails> urlAlreadyExist=urlDetailsRepository.findByLongUrl(url);
        if(urlAlreadyExist.isPresent())
            throw new UrlAlreadyExistException("Short Url for {} already exist",url);
        System.out.println(url);
        String newShortUrl=redisTemplate.opsForList().leftPop(REDIS_QUEUE_NAME);
        System.out.println(newShortUrl);
        UrlDetails urlDetails=new UrlDetails(newShortUrl,url, LocalDateTime.now(),LocalDateTime.now().plusDays(30));
        urlDetailsRepository.save(urlDetails);
        asyncUpdateAvailableIdStatus(newShortUrl);

        return urlDetails;
    }

    @Async
    public void asyncUpdateAvailableIdStatus(String base64Id) {
        Optional<AvailableId> optional = availableIdRepository.findByBase64Id(base64Id);
        if (optional.isPresent()) {
            AvailableId id = optional.get();
            id.setAvailabilityStatus(AvailabilityStatus.USED); // assuming enum
            availableIdRepository.save(id);
        } else {
        throw new RuntimeException("Failed to update the short url status");
        }
    }

}
