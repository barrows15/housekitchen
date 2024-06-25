package com.hkitchen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hkitchen.entities.User;

public class UserDao {
	Connection con = ConnectionProvider.getConnection();


	public User getUserByEmail(String email) {
		String q = "select id, name, password, email from user where email= ?;";
		User user = new User();
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
		return user;
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
	
	public ArrayList<User> getAllUsers() {
		String q = "select id, name, password, email from user where role='USER';";
		ArrayList<User> list = new ArrayList<>();
		
		try {
			Statement stmt = con.createStatement();
			System.out.println("stmt" + stmt);
			ResultSet rs = stmt.executeQuery(q);
			System.out.println("after query");
			System.out.println("rs:" + rs);
			while (rs.next()) {
				// Go to next row by calling next() method
				int id = rs.getInt("id");
				String name=rs.getString("name");
				String password=rs.getString("password");
				String email= rs.getString("email");
				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setPassword(password);
				user.setEmail(email);
				
				System.out.println(user);
				
				list.add(user);
			}
			con.close();
			for (User us :list) {
				System.out.println("list:"+us.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return list;
	}
	
}
