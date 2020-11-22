package ateam.user.db;

import ateam.model.entity.User;
import ateam.model.exception.ConflictException;
import ateam.user.model.exception.UserServiceException;
import ateam.validator.ValidationException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class UserDatabase {

	@Inject
	private DBConnection dbConnection;

	public User createUser(User user) {
		try (Connection con = dbConnection.getConnection()) {
			try {
				con.setAutoCommit(false);
				if(loadUser(user.getUsername(), con) != null) {
					throw new ConflictException("A user with this username already exists!");
				}
				if(loadUserByMail(user.getEmail(), con) != null) {
					throw new ConflictException("A user with this email already exists!");
				}
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

	public User loadUserByMail(String userName) {
		try (Connection con = dbConnection.getConnection()){
			return loadUserByMail(userName, con);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserServiceException("Error load User", e);
		}
	}

	private User loadUserByMail(String userName, Connection connection) throws SQLException {
		PreparedStatement pStmt = connection.prepareStatement("select * from users where email = ?");
		pStmt.setString(1, userName);
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
