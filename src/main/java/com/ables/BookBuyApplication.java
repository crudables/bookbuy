package com.ables;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookBuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookBuyApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner init(StorageService storageService) {
//		return (args) -> {
//            storageService.deleteAll();
//            storageService.init();
//		};
//	}
}
