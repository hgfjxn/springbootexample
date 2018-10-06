package win.hgfdodo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DubboServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboServerApplication.class).web(false).run(args);
    }
}
