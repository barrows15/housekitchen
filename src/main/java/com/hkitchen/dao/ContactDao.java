package com.hkitchen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hkitchen.entities.Contact;


public class ContactDao {

	public ContactDao() {
	}

	Connection con = ConnectionProvider.getConnection();
	Contact contact = new Contact();

	public int save(Contact contact) {
		int result = 0;
		
		String q = "INSERT INTO contact (from_email,to_email,subject,body) VALUES (?,?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, contact.getFromEmail());
			ps.setString(2, contact.getToEmail());
			ps.setString(3, contact.getSubject());
			ps.setString(4, contact.getBody());
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return result;
	}
}
