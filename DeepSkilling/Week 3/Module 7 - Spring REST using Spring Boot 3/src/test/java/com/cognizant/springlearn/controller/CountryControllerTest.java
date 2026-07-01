package com.cognizant.springlearn.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getCountryIndia_shouldReturnIndia() throws Exception {
		mockMvc.perform(get("/country").with(user("user")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").exists())
				.andExpect(jsonPath("$.code").value("IN"))
				.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	void getCountry_withLowerCaseCode_shouldMatchCaseInsensitively() throws Exception {
		mockMvc.perform(get("/countries/in").with(user("user")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("IN"))
				.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	void getCountry_withUnknownCode_shouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/countries/zz").with(user("user")))
				.andExpect(status().isNotFound());
	}

}
