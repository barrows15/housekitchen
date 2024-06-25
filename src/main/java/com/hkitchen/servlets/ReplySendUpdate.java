package com.hkitchen.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkitchen.dao.ConnectionProvider;

/**
 * Servlet implementation class ReplySendUpdate
 */
public class ReplySendUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplySendUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt(request.getParameter("id"));
		String reply = request.getParameter("reply_msg");
		System.out.println(id);
		System.out.println(reply);
		Connection con = ConnectionProvider.getConnection();
		
		try {
			
			String query = "update contact set is_replied = 'YES',Reply_Message = ? where id = ?;";
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, reply);
			ps.setInt(2, id);
			System.out.println("query:" +ps);
			
			int res=ps.executeUpdate();
			
			System.out.println(res);
			
			String query2 ="select from_Email,to_Email,contact.subject from contact where id = ?;";
			System.out.println(query2);
			PreparedStatement ps2 = con.prepareStatement(query2);
			ps2.setInt(1, id);
			System.out.println("query2:" +ps2);
			
			ResultSet result = ps2.executeQuery();
			System.out.println(result);
			if(result.next()) {
				String fromEmail = result.getString("from_Email");
				String toEmail = result.getString("to_Email");
				String subject = result.getString("subject");
				System.out.println(fromEmail + toEmail + subject);
				GEmailSender g = new GEmailSender();
				g.sendEmail(toEmail, fromEmail, subject, reply);
			}
			
			con.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("reply.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
