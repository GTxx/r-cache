package testxx;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class Tservice {
    @Cacheable(cacheNames = "book", cacheManager = "requestCacheManager")
    public Long g(long x) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ", visit book");
        return x;
    }

    @Cacheable(cacheNames = "user", cacheManager = "requestCacheManager")
    public Long user(long x) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ", visit user");
        return x;
    }
}
