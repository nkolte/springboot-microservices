package com.nkolte.departmentservice;

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
				title = "Department-Service APIs",
				description = "Handles operations related to Department.",
				version = "v1.0",
				license = @License(
						name = "Apache 2.0",
						url = "https://apache.org/"
				),
				contact = @Contact(
						name = "NPK",
						email = "npk@gmail.com",
						url = "www.npk.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Organization-Service",
				url = "http://localhost:8082/swagger-ui/index.html#/"
		)
)
public class DepartmentServiceApplication {

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
