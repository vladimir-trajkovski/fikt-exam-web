package mk.edu.uklo.fikt.fiktexamweb;

import mk.edu.uklo.fikt.fiktexamweb.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FiktExamWebApplication {

	@Autowired
	private static UserService userService;



	public static void main(String[] args) {
		SpringApplication.run(FiktExamWebApplication.class, args);
	}

}
