package com.bobsantosjr.openapiv3.springdocsample;

import com.bobsantosjr.openapiv3.springdocsample.core.employee.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringdocSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdocSampleApplication.class, args);
	}

	@Bean
	public Map<Long, Employee> employees() {
		return new HashMap<>();
	}
}
