package com.api.petstore.application.test.pojo;

public class CreateBookRequest {

	private String name;
	private int isbn;
	private String aisle;
	private String author;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getAisle() {
		return aisle;
	}

	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
