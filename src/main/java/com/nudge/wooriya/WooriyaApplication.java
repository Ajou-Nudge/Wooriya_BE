package com.nudge.wooriya;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WooriyaApplication {

	@Value("${jwt.secret}")
	private static String secretKey;

	@Value("${spring.mail.password}")
	private static String password;

	public static void main(String[] args) {
		SpringApplication.run(WooriyaApplication.class, args);
	}

}
