package com.lld.practise.systemDesign.urlShortner.repository;

import com.lld.practise.systemDesign.urlShortner.entity.UrlDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlDetailsRepository extends JpaRepository<UrlDetails,String> {

    Optional<UrlDetails> findByShortUrl(String shortUrl);

    Optional<UrlDetails> findByLongUrl(String url);
}
