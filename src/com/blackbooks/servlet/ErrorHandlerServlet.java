package com.blackbooks.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para gestionar los errores
 */
@WebServlet(name = "ErrorHandlerServlet", urlPatterns = "/error")
public class ErrorHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ErrorHandlerServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if(statusCode.equals(404)) {			
			request.getSession().setAttribute("mainContainer", "err/404.jsp");			
		}else {
			request.getSession().setAttribute("mainContainer", "err/404.jsp");
		}
		request.getSession().setAttribute("headerContainer", null);
		request.getSession().setAttribute("footerContainer", null);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
