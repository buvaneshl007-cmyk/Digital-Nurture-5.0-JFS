package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;

/**
 * Hands on 2: REST - Country Web Service (GET /country)
 * Hands on 3: REST - Get country based on country code (GET /countries/{code})
 */
@RestController
public class CountryController {

	private static final Logger log = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	/**
	 * Hands on 2: Loads the India bean directly from the Spring XML
	 * configuration (country.xml) and returns it. Spring MVC's message
	 * converter serializes the returned Country bean into JSON.
	 */
	@RequestMapping("/country")
	public Country getCountryIndia() {
		log.info("Entering getCountryIndia() method");

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country india = context.getBean("india", Country.class);
		((ClassPathXmlApplicationContext) context).close();

		log.info("Exiting getCountryIndia() method");
		return india;
	}

	/**
	 * Hands on 3: Returns a country matching the given code (case
	 * insensitive), delegating the lookup to CountryService.
	 */
	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code) {
		log.info("Entering getCountry() method, code={}", code);

		Country country = countryService.getCountry(code);

		log.info("Exiting getCountry() method");
		return country;
	}

}
