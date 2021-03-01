package com.microservices;

import com.microservices.model.Role;
import com.microservices.model.User;
import com.microservices.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
public class SpringZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulServerApplication.class, args);
	}

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	//@Bean
	public CommandLineRunner execute() throws Exception {
		return args -> {
			HashSet<Role> roles = new HashSet<Role>(Arrays.asList(new Role(1, "USER")));
			userRepo.save(new User(1,
					"naresh.doniparti@gmail.com",
					"Naresh",
					passwordEncoder.encode("naresh"),
					1,
					false,
					false,
					true,
					roles));
		};
	}

}
