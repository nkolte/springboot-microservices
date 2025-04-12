package com.nkolte.organizationservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Organization-Service APIs",
				description = "Handles operations related to organizations.",
				version = "v1.0",
				contact = @Contact(
						name = "NPK",
						url = "www.npk.com",
						email = "npk@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://apache.org/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Employee-Service",
				url = "http://localhost:8081/swagger-ui/index.html#/"
		)
)
public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
