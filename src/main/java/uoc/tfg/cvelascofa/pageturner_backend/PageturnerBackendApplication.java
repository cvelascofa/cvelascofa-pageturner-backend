package uoc.tfg.cvelascofa.pageturner_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PageturnerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PageturnerBackendApplication.class, args);
	}

}
