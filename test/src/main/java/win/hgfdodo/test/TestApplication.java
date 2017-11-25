package win.hgfdodo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.transaction.TransactionManager;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
//
//        return jpaTransactionManager;
//    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

//        System.out.println("emf:"+entityManagerFactory);
    }
}
