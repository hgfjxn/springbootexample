package win.hgfdodo.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import win.hgfdodo.transaction.bean.Person;
import win.hgfdodo.transaction.service.DemoService;

import java.util.List;

@SpringBootApplication
public class TransactionApplication implements CommandLineRunner {
    private final static Logger log = LoggerFactory.getLogger(TransactionApplication.class);

    @Autowired
    DemoService demoService;


    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        demoService.init();
        List<Person> personList = demoService.getAll();

        if(!CollectionUtils.isEmpty(personList)){
            Person person = personList.get(0);
            log.info("{} cross time", person);
            try{
                demoService.callInner(person);
            }catch (Exception e){
            }
            person = demoService.get(person.getId());
        }
    }
}
