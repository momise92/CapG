package com.capg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class CapGApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CapGApplication.class, args);
	}
	
	@Bean
	public Docket DocApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("com.capg.controller"))
	            .paths(PathSelectors.any())
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.0").title("CapEngament").description("Documentation API v1.0").build());
	}
	
	@Autowired
	DemoData demoData;

	@Override
	public void run(String... args) throws Exception {
		
		demoData.data();

	}

}

