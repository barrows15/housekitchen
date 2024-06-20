package com.hkitchen.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.hkitchen.dao.UserDao;
import com.hkitchen.entities.User;

@MultipartConfig
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public UserServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Thread.sleep(5000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = new User(name,email, password);
		System.out.println(user);
		
		UserDao userDao = new UserDao();
		int result= userDao.save(user);
		
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		if (result >0 ) {
			System.out.println("Done");
			out.println("Done");
//			response.sendRedirect("login.jsp");
			
		} else {
			out.println("Error");
			System.out.println("error. something- try again");
//			response.sendRedirect("signup.jsp");
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
