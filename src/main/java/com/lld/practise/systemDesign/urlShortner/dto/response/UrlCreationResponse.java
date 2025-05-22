package com.lld.practise.systemDesign.urlShortner.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class UrlCreationResponse {
    public String shortUrl;
    public String longUrl;
    public Timestamp creationTimeStamp;
    public Timestamp expirationTimeStamp;
    public String message;
}
