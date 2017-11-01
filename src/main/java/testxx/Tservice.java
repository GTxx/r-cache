package testxx;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class Tservice {
    @Cacheable(cacheNames = "book", cacheManager = "requestCacheManager")
    public Long g(long x) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3l);
        System.out.println("sleep 3s");
        return x;
    }

    @Cacheable(cacheNames = "user", cacheManager = "requestCacheManager")
    public Long user(long x) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2l);
        return x;
    }
}
