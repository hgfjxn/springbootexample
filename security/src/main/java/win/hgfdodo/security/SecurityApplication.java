package win.hgfdodo.security;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SecurityApplication {

  public static void main(String[] args) {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.register(MyConfig.class);
  }
}
