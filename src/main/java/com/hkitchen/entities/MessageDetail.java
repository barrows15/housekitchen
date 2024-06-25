package com.hkitchen.entities;

public class MessageDetail {
	private int contactId;
	private String toEmail;
	private String subject;
	private String message;
	private String userId;
	private String name;
	
	
	public MessageDetail() {
		
	}


	public MessageDetail(int contactId, String toEmail, String subject, String message, String userId, String name) {
		super();
		this.contactId = contactId;
		this.toEmail = toEmail;
		this.subject = subject;
		this.message = message;
		this.userId = userId;
		this.name = name;
	}


	public int getContactId() {
		return contactId;
	}


	public void setContactId(int contactId) {
		this.contactId = contactId;
	}


	public String getToEmail() {
		return toEmail;
	}


	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "MessageDetail [contactId=" + contactId + ", toEmail=" + toEmail + ", subject=" + subject + ", message="
				+ message + ", userId=" + userId + ", name=" + name + "]";
	}
	

}
