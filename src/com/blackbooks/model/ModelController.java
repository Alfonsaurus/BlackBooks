package com.blackbooks.model;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.blackbooks.bean.Book;
import com.blackbooks.bean.User;
import com.blackbooks.exception.DataSourceException;

public class ModelController {

	private MySQLController<User> userModel;
	private MySQLController<Book> bookModel;
	private List<Book> searchResult;

	/**
	 * Constructor del modelo
	 * @param dataSource - El {@link DataSource}
	 * @throws DataSourceException Una exception personalizada
	 */
	public ModelController(DataSource dataSource) throws DataSourceException {
		if (dataSource != null) {
			userModel = new UserDataModel(dataSource, "blackbookstore.user");
			bookModel = new BookDataModel(dataSource, "blackbookstore.book");
		} else
			throw new DataSourceException();
	}

	/**
	 * Recupera la lista de búsqueda de libros reciente {@code List}
	 * 
	 * @return la {@code List} con los libros encontrados (si hay alguno)
	 */
	public List<Book> getSearchResult() {
		return searchResult;
	}

	/*---USER MANAGEMENT METHODS---*/

	/**
	 * Método para encontrar un usuario en la base de datos
	 * 
	 * @param user
	 *            el nombre de usuario
	 * @param password
	 *            la contraseña
	 * @return {@code true} si se encuentra el usuario, {@code false} sino
	 */
	public boolean searchUser(String user, String password) {
		try {
			return userModel.read(user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Recupera el {@link User} de la base de datos
	 * 
	 * @param user el nombre de usuario
	 * @param password la contraseña
	 * @return el {@link User} recuperado
	 */
	public User getUser(String user, String password) {
		try {
			if (searchUser(user, password))
				return userModel.get(user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Crea un {@link User} en la base de datos
	 * 
	 * @param user
	 *            El usuario a crear
	 * @return {@code true} si el usuario se ha creado con éxito, {@code false}
	 *         si no
	 */
	public boolean createUser(User user) {
		try {
			return userModel.create(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*---BOOK MANAGEMENT METHODS---*/
	/**
	 * Recupera una {@code List} de libros y la almacena en el modelo
	 * haciéndola accesible con el método {@code getSearchResult()}
	 * 
	 * @param userName nombre de usuario
	 * @return {@code true} si se ha podido recuperar, {@code false} si no
	 */
	public boolean getUserLoans(String userName) {
		try {
			searchResult = bookModel.getGroup("user_name", userName, true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Devuelve un {@link Book} usando su isbn 
	 * 
	 * @param isbn
	 * @return {@link Book}
	 */
	public Book getBook(String isbn) {
		try {
			return bookModel.get("isbn", isbn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Recupera una {@code List} de {@code Book} 
	 * 
	 * @param column
	 *            Campo de búsqueda
	 * @param value
	 *            Valor que buscar
	 * 
	 * @return {@code List} con los {@code Book} recuperados (si existe alguno)
	 */
	public boolean getSearchResult(String column, String value) {
		try {
			searchResult = bookModel.getGroup(column, value, false);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Actualiza el estado del préstamo del libro
	 * 
	 * @param userName
	 *            El nombre de usuario
	 * @param bookIsbn
	 *            El isbn del libro
	 * @return
	 */
	public boolean updateBookStatus(String userName, String bookIsbn) {
		try {
			return bookModel.update("user_name", userName, "isbn", bookIsbn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
