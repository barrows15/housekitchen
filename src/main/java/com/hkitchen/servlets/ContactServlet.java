package com.hkitchen.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.hkitchen.dao.ContactDao;
import com.hkitchen.entities.Contact;

@MultipartConfig
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContactServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromEmail = request.getParameter("fromEmail");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		
		System.out.println("form data-"+fromEmail+","+email+","+subject+","+body);
		
		ContactDao contactdao = new ContactDao();
		Contact contact = new Contact();
		
		contact.setFromEmail(fromEmail);
		contact.setToEmail(email);
		contact.setSubject(subject);
		contact.setBody(body);
		
		int res = contactdao.save(contact);		
		System.out.println("insert contact table result="+res);
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();		
		if (res == 0) {
			out.println("error - insert contact table is failed");
		}else if(res == 1) {
			GEmailSender gEmailSender = new GEmailSender();
			boolean b = false; 
			b = gEmailSender.sendEmail(email, fromEmail, subject, body);

			if (b) {
				out.println("Done");
				System.out.println("Success");
			} else {
				out.println("error");
				System.out.println("error - send email failed");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doGet(request, response);
	}

}
