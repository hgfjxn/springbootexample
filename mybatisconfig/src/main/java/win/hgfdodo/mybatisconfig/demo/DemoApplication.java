package win.hgfdodo.mybatisconfig.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import win.hgfdodo.mybatisconfig.demo.mapper.UserMapper;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	private final UserMapper userMapper;

    public DemoApplication(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println(userMapper.getUsers("hgf"));
	}
}
