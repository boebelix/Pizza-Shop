package ateam.model.entity;

import ateam.validator.Validator;

public class LogisticsPostInput {
	@Validator.Required
	private int orderId;

	@Validator.Required
	private int userId;

	public LogisticsPostInput() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LogisticsPostInput{" +
			"orderId=" + orderId +
			", userId=" + userId +
			'}';
	}
}
