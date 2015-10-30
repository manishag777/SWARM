package com.worksap.stm.SWARMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/*
 * This class holds information 
 */
@SpringBootApplication
public class RetailStoreApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(RetailStoreApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RetailStoreApplication.class, args);
	}

/*	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");

			container.addErrorPages(error403Page);
		});
	}*/
}
