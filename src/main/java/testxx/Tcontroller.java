package testxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Tcontroller {

    @Autowired
    Tservice tservice;

    @RequestMapping("/test")
    public Long get() throws InterruptedException {
        tservice.g(1l); // 3s
        tservice.g(1l); // already cached, 0s
        tservice.g(1l); // already cached, 0s
        tservice.user(1l); // 2s
        return tservice.g(1); // already cached, 0s
    }

    @RequestMapping("/test1")
    public Long get1() throws InterruptedException {
        return tservice.user(1);
    }
}
