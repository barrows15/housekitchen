package com.hkitchen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hkitchen.entities.Contact;
import com.hkitchen.entities.MessageDetail;


public class ContactDao {

	public ContactDao() {
	}
	
	public int save(Contact contact) {
		int result = 0;
		
		String q = "INSERT INTO contact (from_email,to_email,subject,body) VALUES (?,?,?,?);";
		try {
			Connection con = ConnectionProvider.getConnection();
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
	
	
	public ArrayList<MessageDetail> getAllContacts(){
		ArrayList<MessageDetail> list = new ArrayList<>();
		String query = "SELECT c.id as contact_id, c.to_email, c.subject, c.body as message\r\n"
				+ ", u.id as user_id,u.name \r\n"
				+ "FROM user_register.contact as c\r\n"
				+ "join user_register.user as u   on c.to_email = u.email\r\n"
				+ "where c.is_replied='NO';";
		try {
			Connection con = ConnectionProvider.getConnection();
			System.out.println(query);
			Statement statement = con.createStatement();
			ResultSet set = statement.executeQuery(query);
			while(set.next()) {
				int contactId = set.getInt("contact_id");
				String toEmail = set.getString("to_email");
				String subject = set.getString("subject");
				String message = set.getString("message");
				String userId = set.getString("user_id");
				String name = set.getString("name");
				
				MessageDetail messageDtl = new MessageDetail(contactId,toEmail,subject,message,userId,name);
				System.out.println(messageDtl);
				list.add(messageDtl);
				
			}
			System.out.println(query);

		} catch (Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<MessageDetail> getContactsByEmailId(int id){
		List<MessageDetail> list = new ArrayList<>();
		String query = "SELECT c.id as contact_id, c.to_email, c.subject, c.body as message\r\n"
				+ ", u.id as user_id,u.name \r\n"
				+ "FROM user_register.contact as c\r\n"
				+ "join user_register.user as u   on c.to_email = u.email\r\n"
				+ "where c.is_replied='NO' AND u.id=?;";
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				int contactId = set.getInt("contact_id");
				String toEmail = set.getString("to_email");
				String subject = set.getString("subject");
				String message = set.getString("message");
				String userId = set.getString("user_id");
				String name = set.getString("name");
				
				MessageDetail messageDtl = new MessageDetail(contactId,toEmail,subject,message,userId,name);
				System.out.println(messageDtl);
				list.add(messageDtl);
				
			}
			System.out.println(query);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Contact getContactById(int cid) {
		Contact c = null;
		String query = "SELECT from_Email,to_Email,subject,body,sentAt,is_replied,u.id as user_Id,name,c.id as contact_Id\r\n"
				+"FROM user_register.contact as c\r\n"
				+"join user_register.user as u   on c.to_email = u.email\r\n"
				+"where c.is_replied='NO' AND c.id = ?;";
		
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, cid);
			System.out.println(query);
			ResultSet set = statement.executeQuery();
			
			while(set.next()) {
				int contactId = set.getInt("contact_Id");
				String fromEmail = set.getString("from_email");
				String toEmail = set.getString("to_email");
				String subject = set.getString("subject");
				String body = set.getString("body");
				int userId = set.getInt("user_Id");
				String name = set.getString("name");
				String isReplied = set.getString("is_replied");
				String sentAt = set.getString("sentAt");
				
				c = new Contact(contactId,fromEmail,toEmail,subject,body,sentAt,null,isReplied);
				System.out.println(c);
				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();	
		}
		
		
		return c;
	}
}
