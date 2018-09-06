package com.blackbooks.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blackbooks.bean.Book;

public class BookDataModel implements MySQLController<Book>{

	private DataSource dataSource;
	private String schemaTableName;
	
	public BookDataModel(DataSource dataSource,String schemaTableName) {
		setDataInitialization(dataSource, schemaTableName);
	}
	
	@Override
	public void setDataInitialization(DataSource dataSource,String schemaTableName) {
		this.dataSource = dataSource;
		this.schemaTableName = schemaTableName;
	}

	@Override
	public boolean create(Book book) throws SQLException {
		//TODO Admin form to insert new books
		return false;
	}

	@Override
	public Book get(String column,String value) throws SQLException {
		Book book = new Book();
		String sql = "SELECT * FROM "+schemaTableName+" WHERE "+column+"='"+value+"';";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		connection = dataSource.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()) {
			String isbn = resultSet.getString("isbn");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			Integer pageNum = resultSet.getInt("pageNum");
			String genre = resultSet.getString("genre");
			String synopsis = resultSet.getString("synopsis");
			String user = resultSet.getString("user_name");
			
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setAuthor(author);
			book.setPageNum(pageNum);
			book.setGenre(genre);
			if(synopsis!=null) 
				book.setSynopsis(synopsis);	
			book.setUser(user);
		}
		return book;
	}
	@Override
	public List<Book> getGroup(String column,String value,boolean exact) throws SQLException {
		List<Book> bookList = new ArrayList<>();
		String sql;
		if(!exact) {
			sql = "SELECT * FROM "+schemaTableName+" WHERE "+column+" LIKE '%"+value+"%';";
		}else {
			sql = "SELECT * FROM "+schemaTableName+" WHERE "+column+"='"+value+"';";
		}
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		connection = dataSource.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			Book book = new Book();
			String isbn = resultSet.getString("isbn");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			Integer pageNum = resultSet.getInt("pageNum");
			String genre = resultSet.getString("genre");
			String synopsis = resultSet.getString("synopsis");
			String user = resultSet.getString("user_name");
			
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setAuthor(author);
			book.setPageNum(pageNum);
			book.setGenre(genre);
			if(synopsis!=null) 
				book.setSynopsis(synopsis);
			book.setUser(user);
			bookList.add(book);
			
		}
		return bookList;
	}
	
	@Override
	public boolean read(String column,String value) throws SQLException {
		return false;
	}

	@Override
	public boolean update(String updateColumn, String updateValue, String column, String value) 
			throws SQLException {
		
		String sql = "UPDATE "+schemaTableName+" SET "+updateColumn+"='"+updateValue+"' WHERE "+column+"="+value+";";
		Connection connection = null;
		Statement statement = null;
		
		connection = dataSource.getConnection();
		statement = connection.createStatement();
		if(statement.executeUpdate(sql)!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(String column,String value) throws SQLException {
		//This will never happen...
		return false;
	}	
}
