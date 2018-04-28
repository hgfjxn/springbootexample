package win.hgfdodo.log4jexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Log4jexampleApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(Log4jexampleApplication.class);
	private Logger omit = LoggerFactory.getLogger("omit");
	private Logger queue = LoggerFactory.getLogger("queue");

	public static void main(String[] args) {
		SpringApplication.run(Log4jexampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("logger info");
		logger.debug("logger debug");
		logger.warn("logger warnning");
		logger.error("logger error");

		omit.info("omit info");
		omit.debug("omit debug");
		omit.warn("omit warn");
		omit.error("omit error");

		queue.info("queue info");
		queue.debug("queue debug");
		queue.warn("queue warn");
		queue.error("queue error");
	}
}
