package com.blackbooks.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manages the request from the login/register init.jsp
 */
@WebServlet(name = "UserManagerServlet", urlPatterns = "/user")

public class UserManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManagerServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "login":
				request.setAttribute("userName", request.getParameter("userName"));
				request.setAttribute("userPass", request.getParameter("userPass"));
				break;
			case "register":
				request.setAttribute("firstName", request.getParameter("newFirstName"));
				request.setAttribute("lastName", request.getParameter("newLastName"));
				request.setAttribute("userName", request.getParameter("newUserName"));
				request.setAttribute("password", request.getParameter("newPass"));
				request.setAttribute("email", request.getParameter("newEmail"));
				break;
			case "logged":
				
				break;
			case "logout":
				
				break;
			default:
				break;
			}
		}
		request.setAttribute("event", "user");
		request.getRequestDispatcher("main?action=" + action).forward(request, response);
	}

}
