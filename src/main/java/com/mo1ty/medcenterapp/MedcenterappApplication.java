package com.mo1ty.medcenterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.mo1ty.medcenterapp.repository")
@EntityScan("com.mo1ty.medcenterapp.entity")
public class MedcenterappApplication {

	public static void main(String[] args) {
		// System.setProperty("server.servlet.context-path", "/internal");
		SpringApplication.run(MedcenterappApplication.class, args);
	}

}
