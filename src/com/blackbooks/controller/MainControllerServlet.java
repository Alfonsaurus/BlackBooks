package com.blackbooks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.blackbooks.bean.Book;
import com.blackbooks.bean.User;
import com.blackbooks.exception.DataSourceException;
import com.blackbooks.model.ModelController;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(name="MainControllerServlet", urlPatterns = {"/main"})

public class MainControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/blackbookstore")
	private DataSource dataSource;
	
	private ModelController model;
	
	protected String mainContent;
	protected String footerContent;
	protected String headerContent;
	
	public MainControllerServlet() {		
	}

	/**
	 * En este init() se inicia el modelo pasándole el dataSource de este Servlet
	 * 
	 */
	public void init() throws ServletException {
		try {
			model = new ModelController(dataSource);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		setViewContentUrls(null, "main/init.jsp", null);
	}
	/**
	 * Método para cambiar los valores asociados a los {@code jsp:include} del home.jsp
	 * 
	 * @param header url del archivo jsp para el header
	 * @param main url del archivo jsp para el main
	 * @param footer url del archivo jsp para el footer
	 */
	public void setViewContentUrls(String header, String main, String footer) {
		headerContent = header;
		mainContent = main;
		footerContent = footer;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String urlPattern = null;
		String event =((String) request.getAttribute("event"))!=null?(String) request.getAttribute("event"):"init" ;
		String action = (request.getParameter("action"))!=null?request.getParameter("action"):"init";
				
		switch (event) {
		case "init":
			urlPattern="home.jsp";
			if(request.getSession(false)!=null) {
				request.getSession().invalidate();
				request.getSession(true);
			}
			break;
		case "user":
			urlPattern = doUserRequest(request, response, action);
			break;
		case "book":
			urlPattern = doBookRequest(request, response, action);
			break;
		default:
			break;
		}

		request.getSession().setAttribute("headerContainer", headerContent);
		request.getSession().setAttribute("mainContainer", mainContent);
		request.getSession().setAttribute("footerContainer", footerContent);
		request.getRequestDispatcher(urlPattern).forward(request, response);
	}
	/**
	 * Este método realizará las acciones asociadas al usuario con el parámetro "action" 
	 * como criterio.
	 * 
	 * @param request
	 * @param response
	 * @param action
	 * @return una url según criterio
	 */
	private String doUserRequest(HttpServletRequest request, HttpServletResponse response, String action) {
		String urlPattern = null;
		String initMessage = null;
		User user = new User();
		switch (action) {
		case "init":
			urlPattern = "/home.jsp";
			break;
		case "login":
			String name = (String) request.getAttribute("userName");
			String pass = (String) request.getAttribute("userPass");
			user = model.getUser(name, pass);
			request.getSession().setAttribute("user", user);
			initMessage = (user == null) ? "User can't be found" : null;
			urlPattern = (user != null) ? "user?action=logged" : "home.jsp";
			break;
		case "register":
			// Recover input values from request attributes
			String firstName = (String) request.getAttribute("firstName");
			String lastName = (String) request.getAttribute("lastName");
			String userName = (String) request.getAttribute("userName");
			String password = (String) request.getAttribute("password");
			String email = (String) request.getAttribute("email");
			user = new User(firstName, lastName, userName, password,email);
			// Creates a new user with given parameters
			if (model.createUser(user)) {
				model.getUser(user.getUserName(), user.getPassword());
			}
			// Sets the user session attribute
			request.getSession().setAttribute("user", user);
			// Change values of initMessage & event attributes
			initMessage = (user == null) ? "Can't register that user" : null;
			urlPattern = (user != null) ? "user?action=logged" : "home.jsp";
			break;
		case "logged":
			setViewContentUrls("header/userHeader.jsp", "main/user.jsp", "footer/footerSearch.jsp");
			urlPattern = "book?action=loanList";
			break;
		case "logout":
			request.getSession().removeAttribute("user");
			setViewContentUrls(null, "main/init.jsp", null);
			urlPattern = "/home.jsp";
			break;
		default:
			urlPattern = "/home.jsp";
			break;
		}
		request.getSession().setAttribute("initMessage", initMessage);
		return urlPattern;
	}
	/**
	 * Este método realizará las acciones asociadas a los libros con el parámetro "action" 
	 * como criterio.
	 * @param request
	 * @param response
	 * @param action
	 * @return una url según criterio
	 */
	private String doBookRequest(HttpServletRequest request, HttpServletResponse response, String action) {
		String urlPattern = null;
		User user = (User) request.getSession().getAttribute("user");
		switch (action) {
		case "loanList":
			request.getSession().setAttribute("loanBookList", loanList(user.getUserName()));
			request.getSession().setAttribute("resultContainer", "loanResult.jsp");
			urlPattern="home.jsp";
			break;
		case "loanStatus":
			Book book = (Book) request.getSession().getAttribute("book");
			String operationResult = (setLoanStatus(user, book)) ? "OPERATION SUCCESS" : "OPERATION FAILED";
			request.getSession().setAttribute("operationMessage", operationResult);
			request.getSession().setAttribute("resultContainer", "operationResult.jsp");
			urlPattern="home.jsp";
			break;
		case "search":
			String type = (String) request.getSession().getAttribute("searchType");
			String value = (String) request.getSession().getAttribute("searchValue");
			request.getSession().setAttribute("resultBookList", searchBook(type, value, user.getUserName()));
			request.getSession().setAttribute("resultContainer", "searchResult.jsp");
			urlPattern="home.jsp";
			break;
		case "details":
			String selectedBook = (String) request.getSession().getAttribute("selectedBook");
			request.getSession().setAttribute("book", detailBook(selectedBook));
			request.getSession().setAttribute("resultContainer", "searchDetails.jsp");
			urlPattern="home.jsp";
			break;
		case "lastSearch":
			urlPattern="home.jsp";
			break;
		default:
			break;
		}
		return urlPattern;
	}
	/**
	 * Método para cargar la lista de alquilados del usuario
	 * @param userName el nombre de usuario
	 * @return una {@code List} con los libros en préstamo 
	 */
	private List<Book> loanList(String userName) {
		if (model.getUserLoans(userName))
			return model.getSearchResult();
		else
			return null;
	}
	/**
	 * Método para cambiar el estado del alquiler de un libro
	 * @param user el usuario asociado a la "session"
	 * @param book el libro asociado
	 * @return {@code true} si puede ser cambiado, {@code false} si no se puede
	 */
	private boolean setLoanStatus(User user, Book book) {
		String userName = (user.getUserName().equals(book.getUser())) ? "BlackBooks" : user.getUserName();
		if (model.updateBookStatus(userName, book.getIsbn()))
			return true;
		else
			return false;
	}

	/**
	 * Método para buscar un libro según el criterio seleccionado
	 * @param type el campo en el que buscar
	 * @param value el valor recogido en el input de texto
	 * @param userName el nombre de usuario asociado
	 * @return {@code List} con los libros que coincidan con la búsqueda
	 */
	private List<Book> searchBook(String type, String value, String userName) {
		if (model.getSearchResult(type, value)) {
			List<Book> searchResult = model.getSearchResult();
			return filterSearch(searchResult, userName);
		} else {
			return null;
		}
	}

	/**
	 * Método para filtrar la búsqueda para no 
	 * @param searchResult
	 * @param userName
	 * @return {@code List} con los libros filtrados
	 */
	private List<Book> filterSearch(List<Book> searchResult, String userName) {
		List<Book> userListBook = new ArrayList<>();
		for (Book book : searchResult) {
			if (userName != book.getUser())
				userListBook.add(book);
		}
		return userListBook;
	}

	/**
	 * Método para mostrar los detalles del libro seleccionado
	 * @param isbn el isbn recuperado del libro
	 * @return {@code Book} seleccionado
	 */
	private Book detailBook(String isbn) {
		Book book = model.getBook(isbn);
		return book;
	}	
}
