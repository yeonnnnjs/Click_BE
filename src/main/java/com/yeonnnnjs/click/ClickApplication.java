package com.yeonnnnjs.click;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ClickApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickApplication.class, args);
	}

}
