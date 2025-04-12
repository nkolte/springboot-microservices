package com.nkolte.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Employee-service",
				description = "Manage operations related to employees.",
				version = "v1.0",
				license = @License(
						name = "Apache 2.0",
						url = "test.com"
				),
				contact = @Contact(
						name = "NPK",
						email = "npk@gmail.com",
						url = "www.npk.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Document-Service",
				url = "http://localhost:8080/swagger-ui/index.html#/"
		)
)
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	/*@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}*/

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
}
