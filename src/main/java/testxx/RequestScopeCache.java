package testxx;

import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.concurrent.Callable;


public class RequestScopeCache extends AbstractValueAdaptingCache {

    private String name;

    private ThreadLocalStorage storage;

    public RequestScopeCache(String name){
        super(true);
        this.name = name;
        this.storage = new ThreadLocalStorage(name);
    }

    /**
     * 从缓存获取数据
     * @param key
     * @return
     */
    @Override
    protected Object lookup(Object key) {
        return this.storage.lookup(key);
    }

    /**
     * 获取缓存名称，缓存可以用名称做空间划分
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.storage;
    }

    @Override
    public void put(Object key, Object value) {
        this.storage.put(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        throw new RuntimeException("request scope cache no need to support this method");
    }

    @Override
    public void evict(Object key) {
        throw new RuntimeException("request scope cache no need to support this method");
    }

    @Override
    public void clear() {
        this.storage.resetInheritableCacheHolder();
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        throw new RuntimeException("request scope cache no need to support this method");
    }

    public Boolean isEmpty(){
        return storage.isEmpty();
    }
}
