package win.hgfdodo.exceptionzalando;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class ExceptionZalandoApplication {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.registerModule(new ProblemModule())
				.registerModule(new ConstraintViolationProblemModule());
	}

	public static void main(String[] args) {
		SpringApplication.run(ExceptionZalandoApplication.class, args);
	}
}
