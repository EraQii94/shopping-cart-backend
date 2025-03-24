package com.eCommerceDemo.dream_shops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class DreamShopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamShopsApplication.class, args);
	}

}
