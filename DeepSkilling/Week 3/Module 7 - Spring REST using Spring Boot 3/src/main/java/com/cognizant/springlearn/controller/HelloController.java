package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hands on 1: Hello World RESTful Web Service.
 * GET /hello -> "Hello World!!"
 */
@RestController
public class HelloController {

	private static final Logger log = LoggerFactory.getLogger(HelloController.class);

	@GetMapping("/hello")
	public String sayHello() {
		log.info("Entering sayHello() method");

		String result = "Hello World!!";

		log.info("Exiting sayHello() method");
		return result;
	}

}
