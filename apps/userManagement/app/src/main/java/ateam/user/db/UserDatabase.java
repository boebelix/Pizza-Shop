package ateam.user.db;

import ateam.DBConnection.DBConnector;
import ateam.model.entity.User;
import ateam.model.exception.ConflictException;
import ateam.model.exception.UnknownEntityException;
import ateam.model.exception.UserServiceException;
import ateam.util.BeanUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class UserDatabase {

	@Inject
	private DBConnector dbConnection;

	public User createUser(User user) {
		try (Connection con = dbConnection.getConnection()) {
			try {
				con.setAutoCommit(false);
				User createdUser = createUser(user, con);
				con.commit();
				return createdUser;
			} finally {
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserServiceException("Error create user", e);
		}
	}

	private User createUser(User user, Connection connection) throws SQLException {
		if(loadUser(user.getUsername(), connection) != null) {
			throw new ConflictException("Es existiert bereits ein Nutzer mit diesem Nutzernamen!");
		}
		if(loadUserByMail(user.getEmail(), connection) != null) {
			throw new ConflictException("Es existiert bereits ein Nutzer mit dieser Email!");
		}

		PreparedStatement pstmt = connection.prepareStatement("insert into users (username, first_name, last_name, email, password, postal_code, street, number, city, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getFirstName());
		pstmt.setString(3, user.getLastName());
		pstmt.setString(4, user.getEmail());
		pstmt.setString(5, user.getPassword());
		pstmt.setString(6, user.getPostalCode());
		pstmt.setString(7, user.getStreet());
		pstmt.setString(8, user.getNumber());
		pstmt.setString(9, user.getCity());
		pstmt.setString(10, user.getCountry());
		pstmt.execute();
		ResultSet genKeys = pstmt.getGeneratedKeys();
		while (genKeys.next()) {
			return loadUser(genKeys.getInt(1), connection);
		}
		throw new UserServiceException("Could not insert into users");
	}

	public User updateUser(User user) {
		try (Connection con = dbConnection.getConnection()) {
			try {
				con.setAutoCommit(false);
				User updatedUser = updateUser(user, con);
				con.commit();
				return updatedUser;
			} finally {
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserServiceException("Error update user", e);
		}
	}

	public User updateUser(User user, Connection connection) throws SQLException {
		User oldUser = loadUser(user.getUserId());
		if(oldUser == null) {
			throw new UnknownEntityException("Nutzer existiert nicht!");
		}
		if(!oldUser.getUsername().equals(user.getUsername())) {
			if(loadUser(user.getUsername(), connection) != null) {
				throw new ConflictException("Es existiert bereits ein Nutzer mit diesem Nutzernamen!");
			}
		}
		if(!oldUser.getEmail().equals(user.getEmail())) {
			if(loadUserByMail(user.getEmail(), connection) != null) {
				throw new ConflictException("Es existiert bereits ein Nutzer mit dieser Email!");
			}
		}
		BeanUtils.assign(oldUser, user, true);
		PreparedStatement pstmt = connection.prepareStatement("update users set username = ?, first_name = ?, " +
				"last_name = ?, email = ?, password = ?, postal_code = ?, street = ?, number = ?, city = ?, country = ? " +
				"where id = ?",
			Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, oldUser.getUsername());
		pstmt.setString(2, oldUser.getFirstName());
		pstmt.setString(3, oldUser.getLastName());
		pstmt.setString(4, oldUser.getEmail());
		pstmt.setString(5, oldUser.getPassword());
		pstmt.setString(6, oldUser.getPostalCode());
		pstmt.setString(7, oldUser.getStreet());
		pstmt.setString(8, oldUser.getNumber());
		pstmt.setString(9, oldUser.getCity());
		pstmt.setString(10, oldUser.getCountry());
		pstmt.setInt(11, oldUser.getUserId());
		pstmt.execute();
		return loadUser(oldUser.getUserId(), connection);
	}

	public User loadUser(int userId) {
		try (Connection con = dbConnection.getConnection()){
			return loadUser(userId, con);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserServiceException("Error load User", e);
		}
	}

	private User loadUser(int userId, Connection connection) throws SQLException {
		PreparedStatement pStmt = connection.prepareStatement("select * from users where id = ?");
		pStmt.setInt(1, userId);
		pStmt.execute();
		ResultSet rs = pStmt.getResultSet();
		while (rs.next()) {
			return parseRs(rs);
		}
		return null;
	}

	public User loadUserByMail(String email) {
		try (Connection con = dbConnection.getConnection()){
			return loadUserByMail(email, con);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserServiceException("Error load User", e);
		}
	}

	private User loadUserByMail(String email, Connection connection) throws SQLException {
		PreparedStatement pStmt = connection.prepareStatement("select * from users where email = ?");
		pStmt.setString(1, email);
		pStmt.execute();
		ResultSet rs = pStmt.getResultSet();
		while (rs.next()) {
			return parseRs(rs);
		}
		return null;
	}

	public User loadUser(String userName) {
		try (Connection con = dbConnection.getConnection()){
			return loadUser(userName, con);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserServiceException("Error load User", e);
		}
	}

	private User loadUser(String userName, Connection connection) throws SQLException {
		PreparedStatement pStmt = connection.prepareStatement("select * from users where username = ?");
		pStmt.setString(1, userName);
		pStmt.execute();
		ResultSet rs = pStmt.getResultSet();
		while (rs.next()) {
			return parseRs(rs);
		}
		return null;
	}

	private User parseRs(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setPostalCode(rs.getString("postal_code"));
		user.setStreet(rs.getString("street"));
		user.setNumber(rs.getString("number"));
		user.setCity(rs.getString("city"));
		user.setCountry(rs.getString("country"));
		user.setCreateAt(rs.getTimestamp("created_at"));
		return user;
	}
}
