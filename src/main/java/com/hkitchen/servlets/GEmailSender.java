package com.hkitchen.servlets;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class GEmailSender {

	public GEmailSender() {
	}

	public boolean sendEmail(String to, String from, String subject, String text) {
		boolean flag = false;
		
	System.out.println("step1 : object properties  create and set mail server(port etc");
		// logic
		// smtp properties
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		
		String username = "singhpratibha050172@gmail.com";
		String password = "qyaapxnsopvitanp";
		
	System.out.println("step2 object session - autheticate user with passcode");
		// session
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
	System.out.println("step3 objcet Message - set recepit, sub, body ");
		
		try {

			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setText(text);
			
		System.out.println("step4 call send on transport static object");
		
			Transport.send(message);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}
