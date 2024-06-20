package com.hkitchen.servlets;

import java.io.IOException;

import com.hkitchen.dao.UserDao;
import com.hkitchen.entities.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email);
		System.out.println(password);

		User user = new User();

		UserDao userDao = new UserDao();
		user = userDao.getUserByEmail(email);

		System.out.println(user);
		String loginMessage = "";
		HttpSession session = request.getSession();
		if (user.getEmail() == null) {

			System.out.println("Invalid Login, try again");
			loginMessage = "Invalid Login, try again";
			session.setAttribute("loginMessage", loginMessage);
			response.sendRedirect("login.jsp");

		} else if (!password.equals(user.getPassword())) {

			System.out.println("Invalid password, try again");
			loginMessage = "Invalid password, try again";
			session.setAttribute("loginMessage", loginMessage);
			response.sendRedirect("login.jsp");
		} else {
			System.out.println("Login Successful");
			loginMessage = "Login Successful";
			session.setAttribute("currentuser", user);
			session.setAttribute("loginMessage", loginMessage);
			response.sendRedirect("main.jsp");

		}
	}
}
