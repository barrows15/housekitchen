package com.hkitchen.filter;

import java.io.IOException;

import com.hkitchen.entities.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	public AuthenticationFilter() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("pre servlet filter ");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("currentuser");

		System.out.println("user" + user);

		if (user != null) {
			System.out.println("form userid" + req.getParameter("id"));
			if (req.getParameter("id") == null) {
				res.sendRedirect("main.jsp");
			} else {
				System.out.println("continue to servlet code");
				System.out.println(req.getParameter("id"));
				chain.doFilter(request, response);
				System.out.println("post servlet filter ");
			}
		} else {
			System.out.println("user is null, redirect to login.jsp");
			res.sendRedirect("login.jsp");
		}
	}

}
