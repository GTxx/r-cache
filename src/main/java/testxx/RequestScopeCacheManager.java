package testxx;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;
import java.util.Collections;

public class RequestScopeCacheManager extends AbstractCacheManager{

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return Collections.emptySet();
    }

    protected Cache getMissingCache(String name){
        return new RequestScopeCache(name);
    }
}
