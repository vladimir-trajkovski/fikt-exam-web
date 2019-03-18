package mk.edu.uklo.fikt.fiktexamweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FiktExamWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiktExamWebApplication.class, args);
	}

}
