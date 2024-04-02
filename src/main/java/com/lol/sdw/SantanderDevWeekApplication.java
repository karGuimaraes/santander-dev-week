package com.lol.sdw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SantanderDevWeekApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantanderDevWeekApplication.class, args);
	}

}
