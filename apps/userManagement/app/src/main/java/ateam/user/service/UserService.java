package ateam.user.service;

import ateam.user.Password;
import ateam.user.db.UserDatabase;
import ateam.user.model.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

	@Inject
	private UserDatabase userDatabase;

	public User createUser(User user) {
		user.setPassword(Password.hashPassword(user.getPassword(), Password.genSalt()));
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
