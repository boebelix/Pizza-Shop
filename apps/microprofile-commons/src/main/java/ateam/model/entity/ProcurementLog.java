package ateam.model.entity;


import ateam.validator.Validator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ProcurementLog {
	private String time;

	@Validator.Valid
	@Validator.Required
	private List<ProcurementLogItem> items;

	public ProcurementLog() {
		time = new Timestamp(new Date().getTime()).toString();
	}

	public void setItems(List<ProcurementLogItem> items) {
		this.items = items;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<ProcurementLogItem> getItems() {
		return items;
	}

	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "ProcurementLog{" +
			"items=" + items +
			", time='" + time + '\'' +
			'}';
	}
}
