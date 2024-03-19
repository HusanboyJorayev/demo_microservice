package demo.userregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistryApplication.class, args);
	}

}
