package ateam.procurement.model;

public class ProcurementLogItem {
	private String name;
	private int amount = -1;
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

	public boolean checkValid() {
		if (name == null || amount == -1 || unit == null) {
			return false;
		} else {
			return true;
		}
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

