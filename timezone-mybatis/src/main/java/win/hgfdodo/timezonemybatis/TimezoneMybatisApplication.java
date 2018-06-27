package win.hgfdodo.timezonemybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import win.hgfdodo.timezonemybatis.service.PersonService;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@RestController
@MapperScan(basePackages = "win.hgfdodo.timezonemybatis.mapper")
@SpringBootApplication
public class TimezoneMybatisApplication implements CommandLineRunner {
    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(TimezoneMybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        personService.init();
//        Date date = personService.getBirth();
//        System.out.println(date);

        timeProcess();
    }

    public void timeProcess(){
        //修改系统时区后输出情况
        Date date = new Date();
        System.out.println(date);
        Instant i = date.toInstant();
        System.out.println(i);
        System.out.println(TimeZone.getDefault());

        System.out.println(Instant.parse("2018-06-20T06:46:27.507Z"));
    }

    @GetMapping("/get")
    public void get(@RequestParam("date") Instant date){
        System.out.println(date);
    }
}
