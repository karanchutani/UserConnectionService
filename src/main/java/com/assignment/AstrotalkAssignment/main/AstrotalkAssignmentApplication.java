package com.assignment.AstrotalkAssignment.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is main class.
 * @author Karan
 */
@SpringBootApplication
@EnableJpaAuditing //  Enabling JPA Auditing
@ComponentScan(basePackages = {"com.assignment.AstrotalkAssignment"})
@EnableJpaRepositories(basePackages = {"com.assignment.AstrotalkAssignment.repository"})
@EntityScan("com.assignment.AstrotalkAssignment.model")
public class AstrotalkAssignmentApplication {

	/**
	 * main method.
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AstrotalkAssignmentApplication.class, args);
	}
}
