package com.gerimedica.csvreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
@EntityScan("com.gerimedica.csvreader")
public class CsvReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvReaderApplication.class, args);
	}

}
