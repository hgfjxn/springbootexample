package win.hgfdodo.loader;

import java.util.Arrays;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LoaderApplication implements CommandLineRunner {

  @Autowired
  private Environment environment;

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(LoaderApplication.class);
    springApplication.run(args);
    System.out.println(sun.misc.Launcher.getBootstrapClassPath());
  }

  @Override
  public void run(String... strings) throws Exception {
    System.out.println("default: " + Arrays.asList(environment.getDefaultProfiles()));
    System.out.println("active: " + Arrays.asList(environment.getActiveProfiles()));
    System.out.println("application name: " + environment.getProperty("spring.application.name"));

    System.out.println("profile: " + System.getProperty("spring.profiles.active"));
    System.out.println("profile: " + environment.getProperty("spring.profiles.active"));
    System.out.println("server port: "+ System.getProperty("server.port"));
    System.out.println("server port: "+ environment.getProperty("server.port"));
  }

  @GetMapping("/version")
  public ResponseEntity<String> getVersion() {
    return ResponseEntity.ok(environment.getProperty("spring.application.name"));
  }

}
