package bankid.techinterview.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"bankid.techinterview"})
public class TechinterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechinterviewApplication.class, args);
	}

}
