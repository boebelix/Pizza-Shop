package ateam.user.db;

import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Singleton
public class DBConnection {
	private DataSource dataSource;

	public DBConnection() {
		try {
			Context ctxt = new InitialContext();
			this.dataSource = (DataSource) ctxt.lookup("jdbc/mySQL");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}
