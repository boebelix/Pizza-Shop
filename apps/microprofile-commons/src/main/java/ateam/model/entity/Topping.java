package ateam.model.entity;

import ateam.validator.Validator;

public class Topping {

	private int id;

	@Validator.Required
	//@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "ToppingName muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String name;

	@Validator.Required(errorMessage = "Basismenge benötigt")
	@Validator.Min(value = 1, errorMessage = "Basismenge darf einen Wert von 1 nicht unterschreiten!")
	private int baseAmount;

	@Validator.Required
	@Validator.Min(value = 1, errorMessage = "Die Einheit muss aus mindestens einem Buchstaben bestehen")
	@Validator.Max(value = 16, errorMessage = "Die Einheit darf aus maximal 16 Buchstaben bestehen")
	private String unit;

	public Topping(int id, String name, int baseAmount, String unit) {
		this.id = id;
		this.name = name;
		this.baseAmount = baseAmount;
		this.unit = unit;
	}

	public Topping(String name) {
		this.name = name;
	}

	public Topping() {

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

	public int getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(int baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString()
	{
		String data="topping{";
		data+="id="+id;
		data+="name="+name;
		data+="baseAmount="+baseAmount;
		data+="unit="+unit;
		return data+"}";
	}
}
