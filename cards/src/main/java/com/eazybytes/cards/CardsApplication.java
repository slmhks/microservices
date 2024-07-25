package com.eazybytes.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API documentation",
				description = "This is the REST API documentation for the Cards microservice in Eazy Bank",
				version = "v1",
				contact = @Contact(
						name = "Solomon Hykes",
						email = "solomon@test.com",
						url = "http://www.test.com"
				),
				license = @License(
						name = "Apache License 2.0",
						url = "https://www.apache.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Documentation about the Card Microservice REST APIs can be found in the Eazy Bank web site.",
				url = "http://www.eazybank.com/apis/cards/v1"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
