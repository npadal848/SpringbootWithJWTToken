package com.np.security;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.np.security.entity.User;
import com.np.security.repo.UserRepository;

@SpringBootApplication
public class SpringBootJtwTokenApplication {

	@Autowired
	private UserRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJtwTokenApplication.class, args);
	}

	@PostConstruct
	public void initUsers() {
		List<User> users = List.of(new User(100, "admin", "admin"), new User(101, "root", "admin"),
				new User(102, "nagesh", "password"));
		repo.saveAll(users);
	}

}
