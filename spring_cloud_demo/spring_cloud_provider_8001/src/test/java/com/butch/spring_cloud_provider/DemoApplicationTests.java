package com.butch.spring_cloud_provider;

import com.butch.spring_cloud_api.pojo.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new User().toString());
	}

}
