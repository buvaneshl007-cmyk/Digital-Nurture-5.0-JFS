package com.cognizant.springlearn.model;

/**
 * Simple POJO representing a country. Instances of this class are created
 * from the Spring XML bean configuration (country.xml) and get serialized
 * to JSON by Spring MVC when returned from a @RestController method.
 */
public class Country {

	private String code;
	private String name;

	public Country() {
	}

	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
