package ateam.user.service;

import ateam.model.entity.User;
import ateam.user.db.UserDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

	@Inject
	private UserDatabase userDatabase;

	@Inject
	private PasswordService passwordService;

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
