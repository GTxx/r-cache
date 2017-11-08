package testxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestCacheHandlerIntercept extends HandlerInterceptorAdapter {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestScopeCacheManager cacheManager = applicationContext.getBean(RequestScopeCacheManager.class);
        if (cacheManager.getCacheNames().stream()
                .anyMatch(name -> !((RequestScopeCache) cacheManager.getCache(name)).isEmpty())) {
            throw new RuntimeException("request cache should be empty before executing request");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        RequestScopeCacheManager cacheManager = applicationContext.getBean(RequestScopeCacheManager.class);
        cacheManager.getCacheNames().stream().forEach(name -> cacheManager.getCache(name).clear());
    }
}
