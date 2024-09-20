package com.gusttadev.relationshipccenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RelationshipCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationshipCenterApplication.class, args);
	}

}
