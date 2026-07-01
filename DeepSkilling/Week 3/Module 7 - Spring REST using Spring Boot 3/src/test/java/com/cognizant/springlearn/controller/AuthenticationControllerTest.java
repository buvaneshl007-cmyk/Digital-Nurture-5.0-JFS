package com.cognizant.springlearn.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void authenticate_withValidCredentials_shouldReturnToken() throws Exception {
		mockMvc.perform(get("/authenticate").header("Authorization", basicAuthHeader("user", "pwd")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").exists());
	}

	@Test
	void authenticate_withInvalidCredentials_shouldReturnUnauthorized() throws Exception {
		mockMvc.perform(get("/authenticate").header("Authorization", basicAuthHeader("user", "wrongpwd")))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void authenticate_withoutAuthorizationHeader_shouldReturnUnauthorized() throws Exception {
		mockMvc.perform(get("/authenticate"))
				.andExpect(status().isUnauthorized());
	}

	private String basicAuthHeader(String username, String password) {
		String credentials = username + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
	}

}
