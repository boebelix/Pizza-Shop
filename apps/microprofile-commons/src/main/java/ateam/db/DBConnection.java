package ateam.db;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class DBConnection {
	private DataSource dataSource;

	public DBConnection() {
		try {
			Context ctxt = new InitialContext();
			dataSource = (DataSource) ctxt.lookup("jdbc/mySQL");
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}