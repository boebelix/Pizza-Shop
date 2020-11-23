package ateam.procurement.model;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ProcurementLog {
	private String time;
	private List<ProcurementLogItem> items;

	public ProcurementLog() {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		time = "" + timestamp;
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
