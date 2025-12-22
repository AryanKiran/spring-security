package com.example.demo4.SecurityApp;

import com.example.demo4.SecurityApp.entities.User;
import com.example.demo4.SecurityApp.services.UserService;
import com.example.demo4.SecurityApp.services.jwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityAppApplicationTests {

	@Autowired
	private jwtService	jwtService;

	@Test
	void contextLoads() {
		User user = new User(5L,"aryanKiran@gmail.com","12345");
		String token = jwtService.generateToken(user);
		System.out.println(token);
	}
}
