package ateam.db;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
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
		} catch (final NamingException e) {
			e.printStackTrace();
			System.exit(1); // fail if dataSource can't be found
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	private void checkConnection() {
		try {
			getConnection();
		} catch (final SQLException e) {
			System.err.println("Unable to connect to the database!");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		checkConnection();
	}
}
