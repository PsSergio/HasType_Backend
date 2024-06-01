package com.hastype.api;

import com.hastype.api.controller.UserController;
import com.hastype.api.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@ComponentScan(basePackageClasses = UserController.class)
//@ComponentScan(basePackages = { "com.hastype.api.models.*" })
//@EnableJpaRepositories("com.hastype.api.respository")
//@EntityScan("com.hastype.api.models.*")
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.hastype.api", "com.hastype.api.controller"})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
