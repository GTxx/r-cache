package testxx;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.core.NamedInheritableThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalStorage {
    private final String cacheName;
    private final ThreadLocal<Map<Object, Object>> inheritableCacheHolder;

    public ThreadLocalStorage(String cacheName){
        this.cacheName = cacheName;
        inheritableCacheHolder = new NamedInheritableThreadLocal<Map<Object, Object>>("cache: " + cacheName){
            @Override
            protected Map<Object, Object> initialValue() {
                return new HashMap<>();
            }
        };
    }

    public Object lookup(Object key) {
        return inheritableCacheHolder.get().get(key);
    }

    public void put(Object key, Object value) {
        inheritableCacheHolder.get().put(key, value);
    }

    public void resetInheritableCacheHolder(){
        inheritableCacheHolder.remove();
    }

    public String getCacheName() {
        return cacheName;
    }

    public Boolean isEmpty(){
        return inheritableCacheHolder.get().isEmpty();
    }
}
