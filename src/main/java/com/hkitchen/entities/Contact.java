package com.hkitchen.entities;

public class Contact {

	private int id;
	private String fromEmail;
	private String toEmail;
	private String subject;
	private String body;
	private String sentAt; 
	
	public Contact(int id, String fromEmail, String toEmail, String subject, String body, String sentAt) {
		super();
		this.id = id;
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
		this.sentAt = sentAt;
	}

	public Contact(String fromEmail, String toEmail, String subject, String body) {
		super();
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
	}

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

}
