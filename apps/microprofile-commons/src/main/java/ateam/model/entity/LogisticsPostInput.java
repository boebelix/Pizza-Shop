package ateam.model.entity;

import ateam.validator.Validator;

public class LogisticsPostInput {
	@Validator.Required
	@Validator.Min(1)
	private String orderId;

	@Validator.Required
	@Validator.Min(1)
	private String userId;

	public LogisticsPostInput() {
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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
