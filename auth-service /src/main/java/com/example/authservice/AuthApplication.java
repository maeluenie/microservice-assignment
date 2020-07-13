package com.example.authservice;

import com.example.authservice.user.User;
import com.example.authservice.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialUserData(UserRepository repository){
		return (args) -> {

			User user = new User("tim@gmail.com", "Tim", "1234", "ADMIN");
			repository.save(user);

			user = new User("tam@gmail.com", "Tam", "1234", "USER");
			repository.save(user);

			user = new User("tom@gmail.com", "Tom", "1234", "USER");
			repository.save(user);
		};
	}

}
