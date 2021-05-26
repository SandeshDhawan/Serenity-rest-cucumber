package com.api.petstore.application.test.util;

import com.github.javafaker.Faker;

public class CreateData {
	Faker faker = new Faker();

	public String generateFirstName() {
		return faker.name().firstName();
	}

	public int generateISBNNumber() {
		return faker.number().randomDigit();
	}

	public String generateAisle() {
		return faker.name().firstName() + "_" + faker.number().digit();
	}

	public String generateAuthor() {
		return faker.book().author();
	}
}
