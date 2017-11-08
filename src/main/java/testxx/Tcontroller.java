package testxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Tcontroller {

    @Autowired
    Tservice tservice;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/test")
    public Long get() throws InterruptedException {
        System.out.println("\nthread name: " + Thread.currentThread().getName());
        tservice.g(1l); // 3s
        tservice.g(1l); // already cached, 0s
        tservice.g(1l); // already cached, 0s
        tservice.user(1l); // 2s
        RequestScopeCacheManager cacheManager = applicationContext.getBean(RequestScopeCacheManager.class);
        Runnable runnable = () -> {
            Object result = cacheManager.getCache("book").get(1l);
            assert result != null;
            try {
                tservice.g(1l); // already cached, 0s
            } catch (Exception e){

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        return tservice.g(1); // already cached, 0s
    }

    @RequestMapping("/test1")
    public Long get1() throws InterruptedException {
        return tservice.user(1);
    }
}
