package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		log.info("Entering main() method of SpringLearnApplication");
		SpringApplication.run(SpringLearnApplication.class, args);
		log.info("SpringLearnApplication started successfully");

		// Hands on 2 - load SimpleDateFormat bean from Spring XML configuration
		displayDate();
	}

	/**
	 * Hands on 2: Spring Core - Load SimpleDateFormat from Spring Configuration XML.
	 * Loads the "dateFormat" bean defined in date-format.xml and uses it to parse
	 * a sample date string.
	 */
	public static void displayDate() {
		log.info("Entering displayDate() method");

		ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);

		try {
			Date date = format.parse("31/12/2018");
			log.info("Parsed date: {}", date);
			System.out.println("Parsed date: " + date);
		} catch (ParseException e) {
			log.error("Error parsing date", e);
		}

		((ClassPathXmlApplicationContext) context).close();
		log.info("Exiting displayDate() method");
	}

}
