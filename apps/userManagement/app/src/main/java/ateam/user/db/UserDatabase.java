package ateam.user.db;

import ateam.user.model.entity.User;
import ateam.user.model.exception.UserServiceException;

import java.sql.*;

public class UserDatabase {

	public int createUser(User user) {
		try (Connection con = DBConnection.getInstance().getConnection()) {
			try {
				con.setAutoCommit(false);
				if(loadUser(user.getUsername()) != null) {
					throw new IllegalArgumentException("A user with this username already exists!");
				}
				int createdUserId = createUser(user, con);
				con.commit();
				return createdUserId;
			} finally {
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserServiceException("Error create user", e);
		}
	}
	private int createUser(User user, Connection connection) throws SQLException {
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
				user.setUserId(genKeys.getInt(1));
				return genKeys.getInt(1);
			}
			throw new UserServiceException("Could not insert into users");
	}

	public User loadUser(int userId) {
		try (Connection con = DBConnection.getInstance().getConnection()){
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

	public User loadUser(String userName) {
		try (Connection con = DBConnection.getInstance().getConnection()){
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
		user.setCreateDt(rs.getTimestamp("created_at").toLocalDateTime());
		return user;
	}

}
