package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMysql {
	
	static String serverName = "localhost";
	static String mydatabase = "mysql";
	static String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase;
	static String username = "root";
	static String password = "database1234";
	

	public ConnectionMysql() {
	}

	public static String status = "Not connected";
	
	public static Connection getConectionMysql() {
		Connection connection = null;
		
		String driverName = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driverName);
		
			connection = DriverManager.getConnection(url, username, password);
			
			if (connection != null) {
				status = ("STATUS--->Successfully connected!");
			} else {
				status = ("STATUS--->Could not connect.");
			}
			System.out.println(status);
			return connection;
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Could not connect to the Database.");
            return null;
		}
		
	}
	
	public static String statusConnection() {
        return status;
    }
	
}
