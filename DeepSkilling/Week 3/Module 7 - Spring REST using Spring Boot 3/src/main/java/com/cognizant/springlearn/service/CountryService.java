package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cognizant.springlearn.model.Country;

/**
 * Hands on 3: REST - Get country based on country code.
 * Loads the country list from country.xml and performs a case-insensitive
 * match on country code.
 */
@Service
public class CountryService {

	private static final Logger log = LoggerFactory.getLogger(CountryService.class);

	@SuppressWarnings("unchecked")
	public Country getCountry(String code) {
		log.info("Entering getCountry() method, code={}", code);

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countryList = (List<Country>) context.getBean("countryList");

		// Lambda expression used instead of a manual iteration loop
		Country match = countryList.stream()
				.filter(country -> country.getCode().equalsIgnoreCase(code))
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No country found for code: " + code));

		((ClassPathXmlApplicationContext) context).close();

		log.info("Exiting getCountry() method");
		return match;
	}

}
