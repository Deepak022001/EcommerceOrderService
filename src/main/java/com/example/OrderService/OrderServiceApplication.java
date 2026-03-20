package com.example.OrderService;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrderServiceApplication {

	public static void main(String[] args) {
		Dotenv dotenv=Dotenv.configure().load();
		dotenv.entries().forEach(x->System.setProperty(x.getKey(),x.getValue()));
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
