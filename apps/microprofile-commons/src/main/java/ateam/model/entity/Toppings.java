package ateam.model.entity;

import ateam.validator.Validator;

public class Toppings {

	private int id;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "ToppingName muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String name;

	public Toppings() {
	}

	public Toppings(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Toppings(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
