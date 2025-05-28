package com.lld.practise.RateLimitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Marker interface for all strategy-specific configs
 interface RateLimiterConfig {}

// Fixed Window Config
 class FixedWindowConfig implements RateLimiterConfig {
    private final int maxRequests;
    private final int windowSizeInSeconds;

    public FixedWindowConfig(int maxRequests, int windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInSeconds = windowSizeInSeconds;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public int getWindowSizeInSeconds() {
        return windowSizeInSeconds;
    }
}

// Token Bucket Config
 class TokenBucketConfig implements RateLimiterConfig {
    private final int bucketCapacity;
    private final int refillRatePerSecond;

    public TokenBucketConfig(int bucketCapacity, int refillRatePerSecond) {
        this.bucketCapacity = bucketCapacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    public int getBucketCapacity() {
        return bucketCapacity;
    }

    public int getRefillRatePerSecond() {
        return refillRatePerSecond;
    }
}

// Strategy interface
 interface RateLimitStrategy {
    boolean allowRequest(String clientId);
}


 class FixedWindowStrategy implements RateLimitStrategy {
    private final FixedWindowConfig config;
    private final Map<String, Window> clientWindows = new ConcurrentHashMap<>();

    private static class Window {
        int count;
        long startTime;
    }

    public FixedWindowStrategy(FixedWindowConfig config) {
        this.config = config;
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis() / 1000;
        Window window = clientWindows.computeIfAbsent(clientId, id -> new Window());

        if (window.startTime == 0 || currentTime - window.startTime >= config.getWindowSizeInSeconds()) {
            window.startTime = currentTime;
            window.count = 1;
            return true;
        }

        if (window.count < config.getMaxRequests()) {
            window.count++;
            return true;
        }

        return false;
    }
}

 class TokenBucketStrategy implements RateLimitStrategy {
    private final TokenBucketConfig config;
    private final Map<String, Bucket> clientBuckets = new ConcurrentHashMap<>();

    private static class Bucket {
        double tokens;
        long lastRefillTime;
    }

    public TokenBucketStrategy(TokenBucketConfig config) {
        this.config = config;
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        Bucket bucket = clientBuckets.computeIfAbsent(clientId, id -> {
            Bucket b = new Bucket();
            b.tokens = config.getBucketCapacity();
            b.lastRefillTime = currentTime;
            return b;
        });

        refillTokens(bucket, currentTime);

        if (bucket.tokens >= 1) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    private void refillTokens(Bucket bucket, long currentTime) {
        long timeElapsed = currentTime - bucket.lastRefillTime;
        double tokensToAdd = (timeElapsed / 1000.0) * config.getRefillRatePerSecond();

        bucket.tokens = Math.min(config.getBucketCapacity(), bucket.tokens + tokensToAdd);
        bucket.lastRefillTime = currentTime;
    }
}

// Main RateLimiter class
public class RateLimiter {
    private final RateLimitStrategy strategy;

    public RateLimiter(RateLimitStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean allowRequest(String clientId) {
        return strategy.allowRequest(clientId);
    }
}

// Example Main usage
 class Main {
    public static void main(String[] args) throws InterruptedException {
        // Fixed Window Rate Limiter
        FixedWindowConfig fwConfig = new FixedWindowConfig(5, 10); // 5 requests per 10 seconds
        RateLimiter fixedLimiter = new RateLimiter(new FixedWindowStrategy(fwConfig));

        for (int i = 0; i < 7; i++) {
            System.out.println("Fixed Window: " + fixedLimiter.allowRequest("user1"));
        }

        // Token Bucket Rate Limiter
        TokenBucketConfig tbConfig = new TokenBucketConfig(5, 2); // 5 capacity, 2 tokens per second
        RateLimiter tokenLimiter = new RateLimiter(new TokenBucketStrategy(tbConfig));

        for (int i = 0; i < 7; i++) {
            System.out.println("Token Bucket: " + tokenLimiter.allowRequest("user1"));
            Thread.sleep(300);
        }
    }
}

