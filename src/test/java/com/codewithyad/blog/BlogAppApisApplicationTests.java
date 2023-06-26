package com.codewithyad.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codewithyad.blog.repositores.UserRepositories;

@SpringBootTest
class BlogAppApisApplicationTests {
	@Autowired
	private UserRepositories userRepo;
	@Test
	void contextLoads() {
	}
	
}
