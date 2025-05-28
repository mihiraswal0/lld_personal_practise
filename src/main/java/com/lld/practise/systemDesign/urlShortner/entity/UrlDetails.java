package com.lld.practise.systemDesign.urlShortner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "url_details")
public class UrlDetails {

    @Id
    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "long_url", length = 50, nullable = false, unique = true)
    private String longUrl;

    @Column(name = "creation_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creationTime;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

}