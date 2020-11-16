package ateam.user.model.response;

import ateam.user.model.entity.User;

import java.util.UUID;

public class LoginResponse {
	private final UUID token;
	private final User user;

	public LoginResponse(UUID token, User user) {
		this.token = token;
		this.user = user;
	}

	public UUID getToken() {
		return token;
	}

	public User getUser() {
		return user;
	}
}
