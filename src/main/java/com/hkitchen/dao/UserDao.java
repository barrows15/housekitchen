package com.hkitchen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hkitchen.entities.User;

public class UserDao {
	Connection con = ConnectionProvider.getConnection();
	User user = new User();

	public User getUserByEmail(String email) {
		String q = "select id, name, password, email from user where email= ?;";
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, email);
			System.out.println("before query");
			System.out.println("ps" + ps);
			ResultSet rs = ps.executeQuery();
			System.out.println("after query");
			System.out.println("rs:" + rs);
			while (rs.next()) {
				// Go to next row by calling next() method

				System.out.println(rs.getInt(1));
				user.setId(rs.getInt(1));
				System.out.println(rs.getString(2));
				user.setName(rs.getString(2));
				System.out.println(rs.getString(3));
				user.setPassword(rs.getString(3));
				System.out.println(rs.getString(4));
				user.setEmail(rs.getString(4));
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return this.user;
	}

	public int save(User user) {
		int result = 0;
		String q = "insert into user(name,password,email) values(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return result;
	}

	public int update(User user) {
		int result = 0;
		String q = "Update user set name=?,password=?,email=? where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getId());
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return result;
	}
	
}
