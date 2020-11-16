package ateam.user.service;

import ateam.model.UnauthorizedException;
import ateam.user.Password;
import ateam.user.model.request.LoginData;
import ateam.user.model.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class AccessService {

	@Inject
	private UserService userService;

	private Map<UUID, Integer> logins = new HashMap<>();

	public Integer getUserIdIfLoggedIn(UUID uuid) {
		if(uuid == null)
			return null;
		return this.logins.get(uuid);
	}

	public UUID login(int userId) {
		UUID uuid = UUID.randomUUID();
		this.logins.put(uuid, userId);
		return uuid;
	}

	public boolean logout(UUID loginId) {
		return this.logins.remove(loginId) != null;
	}

	public UUID login(LoginData loginData) throws UnauthorizedException {
		User user = userService.loadUser(loginData.getUsername());
		if(user == null) {
			throw new UnauthorizedException("User does not exist!");
		}
		if(!Password.checkPassword(loginData.getPassword(), user.getPassword())) {
			throw new UnauthorizedException("Password invalid!");
		}
		return this.login(user.getUserId());
	}

}
