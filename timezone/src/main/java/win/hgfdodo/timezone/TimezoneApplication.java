package win.hgfdodo.timezone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import win.hgfdodo.timezone.bean.Person;
import win.hgfdodo.timezone.service.PersonService;
import win.hgfdodo.timezone.utils.Utils;

import java.time.Instant;
import java.util.Date;

@RestController
@SpringBootApplication
public class TimezoneApplication implements CommandLineRunner {

    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(TimezoneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        personService.init();

        System.out.println("\n-----java time object diff------");
        timeDiff();

        System.out.println("\n------jvm and mysql Date------");
    }

    @GetMapping("/birth")
    public Date compare(){
        //计算机时区，数据库时区和JVM时区是怎么关联的？
        //方法一：JVM运行时增加参数，指定时区 -Duser.timezone=GMT+08
        //方法二：直接在程序中设置时区。System.setProperty(“user.timezone”,”GMT +08″);

        /**
         * 数据库是系统时间：+08，jvm是+01时区，
         * 当前时间：2018-06-20 11:11:45 （+08）
         * 数据库date：2018-06-20 11:11:45（当JVM为+01时区时，数据库事件是2018-06-20 04:11:45，说明：数据库通过插入的数据自动判断 JVM时区，并存在库中的时间是按照JVM中的时区存储）
         * 程序获取时间：2018-06-20T03:11:45.000+0000 （取数据会自动返回标准时区）
         */
        System.out.println("JVM时区："+ Utils.jvmTimezone());
        return personService.getBirth();
    }



    public void timeDiff(){
        Date a = new Date();
        Instant b = a.toInstant();
        System.out.println("date: "+ a);
        System.out.println("instant: "+ b);
    }


}
