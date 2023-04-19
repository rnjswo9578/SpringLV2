package com.example.bloglv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class BlogLv2Application {

	public static void main(String[] args) {
		SpringApplication.run(BlogLv2Application.class, args);
	}

}
