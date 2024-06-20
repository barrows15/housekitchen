package com.hkitchen.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside logout servlet");
		
		HttpSession session = request.getSession();
		
		System.out.println(session);
		
		if (session != null) {
			
			System.out.println("session exists");
			session.removeAttribute("login_msg");
			session.removeAttribute("user");
			session.invalidate();
		}
		
		/*
		 * RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		 * rd.forward(request, response);
		 */
		response.sendRedirect("index.jsp");
		System.out.println("redirect to index page");
	}
}
