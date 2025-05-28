package com.lld.practise.systemDesign.urlShortner.util;


public final class Constants {
    public static final String URL_CONTROLLER_PREFIX="/api/v1/url_shortener";
    public static final String URL_REDIRECT_PREFIX="/redirect/{shortUrl}";
    public static final String URL_CREATE_PREFIX="/create";
    public static final String REFILL_DB ="/refill";
    public static final String REDIS_QUEUE_NAME="url_queue";

    public static final String BASE64_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_"; // 64 chars

}
