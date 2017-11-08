package testxx;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport{

    @Bean(name = "requestCacheManager")
    @Primary
    CacheManager requestScopeCacheManager(){
        CacheManager cacheManager = new RequestScopeCacheManager();
        return cacheManager;
    }
}
