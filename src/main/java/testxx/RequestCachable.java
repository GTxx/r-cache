package testxx;

import org.springframework.cache.annotation.Cacheable;

@Cacheable(cacheManager = "requestCacheManager")
public @interface RequestCachable {
}
