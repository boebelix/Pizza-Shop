package ateam.user.service;

import ateam.model.exception.UserServiceException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Singleton;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Objects;

@Singleton
public class PasswordService {

	private static final int iterations = 4096;
	private static final int saltLength = 32;


	public String hashPassword(String password) {
		return hashPassword(password, genSalt());
	}

	public boolean checkPassword(String password, String storedPassword) {
		String[] saltAndPassword = storedPassword.split("\\$");
		byte[] salt = Base64.getDecoder().decode(saltAndPassword[0]);
		String hashedPassword = hashPassword(password, salt);
		return Objects.equals(hashedPassword, storedPassword);
	}

	private String hashPassword(String password, byte[] salt) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			SecretKey key = skf.generateSecret(new PBEKeySpec(password.toCharArray(), salt, iterations, 128));
			String saltAsString = Base64.getEncoder().encodeToString(salt);
			String saltedPassword = Base64.getEncoder().encodeToString(key.getEncoded());
			return saltAsString + '$' + saltedPassword;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new UserServiceException("Error hashing Password!", e);
		}
	}

	private byte[] genSalt() {
		return new SecureRandom().generateSeed(saltLength);
	}

}
