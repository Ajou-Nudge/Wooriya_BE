package com.nudge.wooriya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WooriyaApplication {
	public static void main(String[] args) {
		SpringApplication.run(WooriyaApplication.class, args);
	}

}
