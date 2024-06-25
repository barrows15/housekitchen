package com.hkitchen.gemail;

public class App {

	public App() {
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		String to = "shailendrarsingh@gmail.com";
		String from = "singhpratibha050172@gmail.com";
		String subject = "test gemail";
		String text = "test gemail sender app message";
		GEmailSender gEmailSender = new GEmailSender();
		boolean b=false;
		b = gEmailSender.sendEmail(to, from, subject, text);
		if (b) {
			System.out.println("Success");
		} else {
			System.out.println("failed");
		}
	}
}
