package com.capg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;


@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class CapGApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CapGApplication.class, args);
	}
	
	@Autowired
	DemoData demoData;

	@Override
	public void run(String... args) throws Exception {
		
		demoData.data();

	}

}

