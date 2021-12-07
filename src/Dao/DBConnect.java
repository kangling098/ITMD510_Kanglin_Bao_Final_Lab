package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect  {

	protected Connection connection;
	public Connection getConnection() {
		return connection;
	}

//	private static String url = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
//	private static String username = "db510";
//	private static String password = "510";
	
	private static String DB_URL = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
	private static String USER = "fp510";
	private static String PASS = "510";
	
//	 Code database URL
	// private static final String DB_URL = "jdbc:mysql://localhost:3306/myMysql?autoReconnect=true&useSSL=true&allowPublicKeyRetrieval=true";
	// // Database credentials
	// private static final String USER = "root", PASS = "Qq175906291";

	public DBConnect() {
//		connection = DriverManager.getConnection(DB_URL, USER, PASS);
		try {
//			connection = DriverManager.getConnection(url, username, password);
			System.out.println("connect before");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connect ok");
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
      e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

	
}
