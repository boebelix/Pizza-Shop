package ateam.logistics.model;

public class LogisticsPostInput {
	private int orderId = -1;
	private int userId = -1;

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

	public boolean checkValid() {
		if (orderId == -1 || userId == -1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "LogisticsPostInput{" +
			"orderId=" + orderId +
			", userId=" + userId +
			'}';
	}
}
