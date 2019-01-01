package com.quizme.dao;

import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.RootLogger;

/**
 * 
 * @author Satya S Vundavalli
 * 
 * This is the basic code for establishing connection with the database. This is referenced.
 *
 */

public class ConnectionDAO {

	
	static Connection con;

	static Logger logger=RootLogger.getLogger(ConnectionDAO.class.getName());
	
	 
	public static Connection openConnection() {
		PropertyConfigurator.configure("log4j.properties");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:6060/quizme";
		String username = "root";
		String password = "admin123";
		PropertyConfigurator.configure("log4j.properties");
		try {
			//Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			 e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection() {

		PropertyConfigurator.configure("log4j.properties");
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			 e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
