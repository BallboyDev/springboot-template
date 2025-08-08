package com.ballboy.shop;

import com.ballboy.shop.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ShopApplicationTests {

	@MockBean
	private JwtUtil jwtUtil;

	@Test
	void contextLoads() {
	}

}