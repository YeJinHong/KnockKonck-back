package com.test.knockknockback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KnockKnockBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnockKnockBackApplication.class, args);
	}

}
