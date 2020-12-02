package ateam.model.entity;

import ateam.validator.Validator;

public class ProcurementLogItem {
	@Validator.Required
	@Validator.Min(1)
	private String name;

	@Validator.Required
	@Validator.Min(1)
	private int amount;

	@Validator.Required
	@Validator.Min(1)
	private String unit;

	public ProcurementLogItem() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public String getUnit() {
		return unit;
	}


	@Override
	public String toString() {
		return "{" +
			"name='" + name + '\'' +
			", amount=" + amount +
			", unit='" + unit + '\'' +
			'}';
	}
}

