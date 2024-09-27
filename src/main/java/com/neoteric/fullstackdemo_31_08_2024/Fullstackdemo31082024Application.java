package com.neoteric.fullstackdemo_31_08_2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.neoteric.fullstackdemo_31_08_2024")
public class Fullstackdemo31082024Application {

	public static void main(String[] args) {
		SpringApplication.run(Fullstackdemo31082024Application.class, args);
	}

}
