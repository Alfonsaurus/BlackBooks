package com.blackbooks.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookManagerServlet
 */
@WebServlet(name = "BookManagerServlet", urlPatterns = "/book")

public class BookManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookManagerServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "search":
				request.getSession().setAttribute("searchType", request.getParameter("searchType"));
				request.getSession().setAttribute("searchValue", request.getParameter("searchParam"));
				break;
			case "loanList":
				break;
			case "details":
				request.getSession().setAttribute("selectedBook", request.getParameter("value"));
				break;
			case "lastSearch":
				request.getSession().setAttribute("resultContainer", "searchResult.jsp");
				break;
			case "loanStatus":

				break;
			default:
				break;
			}
		}
		request.setAttribute("event", "book");
		request.getRequestDispatcher("main?action=" + action).forward(request, response);
	}

}
