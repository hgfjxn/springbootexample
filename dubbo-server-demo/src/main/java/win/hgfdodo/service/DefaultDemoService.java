package win.hgfdodo.service;
import com.alibaba.dubbo.config.annotation.Service;
import win.hgfdodo.DemoService;

@Service(
        version="${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DefaultDemoService implements DemoService {

    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
