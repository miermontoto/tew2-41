package com.tewrrss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona las conexiones con la base de datos.
 * La base de datos se getsiona a través de SGBD Hypersonic, que se
 * carga con un script de datos inicial "localDB.script".
 */
public class Database {
	private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String DB_USER = "SA";
	private static final String DB_PASS = "";
	private static final String DB_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	private static Connection conn = null;

	public Database() {
		try {
			Class.forName(DB_DRIVER); // Cargar el driver

			if (conn == null) {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Error loading driver: " + e.getMessage());
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) { handleException(e); }
	}

	public ResultSet executeQuery(String query) {
		ResultSet rs = null;

		try {
			rs = conn.prepareStatement(query).executeQuery();
		} catch (SQLException e) { handleException(e); }

		return rs;
	}

	public int executeUpdate(String query) {
		int result = 0;

		try {
			result = conn.prepareStatement(query).executeUpdate();
		} catch (SQLException e) { handleException(e); }

		return result;
	}

	public void handleException(Exception e) {
		System.err.println("DB handled exception: " + e.getMessage());
		System.err.println("The connection is being shut down.");
		this.closeConnection();
	}
}
