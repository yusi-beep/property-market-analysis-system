package com.real.estate.analyzer.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbs {

	public static void main(String[] args) {

		 //String jdbcUrl ="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String jdbcUrl = "jdbc:mysql://localhost:3306/property-market-analyzer?useSSL=false&serverTimezone=UTC";
		String user = "joyusufov";
		String pass = "joyusufov";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection myConn =
					DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!!!");
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
