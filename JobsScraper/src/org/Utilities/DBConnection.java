package org.Utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// final: No chance to inherit this class and override the methods.
public final class DBConnection {

	private static Connection connection = null;

	// private Constructor
	private DBConnection() {

	}

	// Lazy Initialization, for thread safe, synchronized needed.
	public static synchronized Connection getConnection() {
		if (connection == null) {
			try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:8060/test";
				String user = "test";
				String password = "123";

				Class.forName(driver).newInstance();
				connection = DriverManager.getConnection(url, user, password);

			} catch (SQLException e) {
				throw new RuntimeException("SQL Exception", e);
			} catch (InstantiationException e) {
				throw new RuntimeException("SQL instantiation Exception", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("SQL IllegalAccess Exception", e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("SQL ClassNotFound Exception", e);
			}

		}
		return connection;
	}
}
