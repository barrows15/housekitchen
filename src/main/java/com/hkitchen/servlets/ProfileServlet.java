package com.hkitchen.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.hkitchen.dao.UserDao;
import com.hkitchen.entities.User;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email);
		System.out.println(password);

		User user = new User(id, name, email, password);

		UserDao userDao = new UserDao();
		int res = userDao.update(user);

		System.out.println(res);
		String profileMessage = "";
		HttpSession session = request.getSession();

		if (res == 0) {
			System.out.println("not updated");
			profileMessage = "Not updated! something wrong";
			session.setAttribute("profileMessage", profileMessage);
		} else {
			System.out.println("updated");
			profileMessage = "Profile updated! Successfully";
			session.setAttribute("currentuser", user);
			session.setAttribute("profileMessage", profileMessage);
		}
		response.sendRedirect("main.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
