package org.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.HashMap;

/**
 * 改善一下 cacheName的最佳实践方法，目前主要用来不同的cache name不同的过期时间，可以扩展
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.cache.redis")
public class MyCacheProperties {
    private HashMap<String, Duration> cacheNameConfig;
}