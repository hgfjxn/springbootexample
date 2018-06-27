package win.hgfdodo.aop.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopConfig {

    @Pointcut("within(win.hgfdodo.aop.service..*)")
    public void servicePointCut(){
    }

    @Pointcut("execution(* win.hgfdodo.aop.service.Open.*(..))")
    public void openPointCut(){

    }

    @Before("openPointCut()")
    public void beforeOpen(){
        System.out.println("before opening");
    }

    @Before("servicePointCut()")
    public void beforePoint(){
        System.out.println("before doing");
    }

    @After("servicePointCut()")
    public void AfterPoint(){
        System.out.println("after doing");
    }
}
