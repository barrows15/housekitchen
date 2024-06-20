package com.hkitchen.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	public static Connection getConnection() {
		Connection con=null;
		try {
//		Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_register", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return con;
	}
}
