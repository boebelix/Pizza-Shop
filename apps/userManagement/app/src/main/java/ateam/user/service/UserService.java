package ateam.user.service;

import ateam.user.db.UserDatabase;
import ateam.model.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

	private final UserDatabase userDatabase;

	private final PasswordService passwordService;

	@Inject
	public UserService(final UserDatabase userDatabase, final PasswordService passwordService) {
		this.userDatabase = userDatabase;
		this.passwordService = passwordService;
	}

	public User createUser(User user) {
		user.setPassword(passwordService.hashPassword(user.getPassword()));
		userDatabase.createUser(user);
		return user;
	}

	public User loadUser(int id) {
		return userDatabase.loadUser(id);
	}

	public User loadUser(String username) {
		return userDatabase.loadUser(username);
	}

}
