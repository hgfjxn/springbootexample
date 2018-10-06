package win.hgfdodo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import win.hgfdodo.DemoService;

@RestController
public class DemoController {

    @Reference(version = "${demo.service.version}", application ="${dubbo.application.id}", url = "dubbo://localhost:12345")
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        return demoService.sayHello(name);
    }
}
