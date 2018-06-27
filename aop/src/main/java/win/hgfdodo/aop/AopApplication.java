package win.hgfdodo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import win.hgfdodo.aop.service.Close;
import win.hgfdodo.aop.service.Open;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AopApplication implements CommandLineRunner {

    @Autowired
    private Open open;

    @Autowired
    private Close close;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        open.delock();
//        open.openService();
//        close.closeService();
    }
}
