package com.webservice.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class ConnectionMysql {
	
	private final static Logger LOGGER = Logger.getLogger(ConnectionMysql.class.getName());
	
	static String serverName = "localhost";
	static String mydatabase = "cities";
	static String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String username = "root";
	static String password = "";
	
	private static Connection connection = null;
	

	public ConnectionMysql() {
	}

	public static String status = "Not connected";
	
	public static Connection getConectionMysql() {
		if (connection == null) {
			connection = connect();
		}
		return connection;		
	}
	
	private static Connection connect() {
		String driverName = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driverName);		
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			LOGGER.log(LOGGER.getLevel(), "Não foi possível realizar a conexão!", e.fillInStackTrace());
            return null;
		}
	}
	
	public static String statusConnection() {
        return status;
    }
	
}
