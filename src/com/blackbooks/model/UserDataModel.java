package com.blackbooks.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.blackbooks.bean.User;

public class UserDataModel implements MySQLController<User> {

	private DataSource dataSource;
	private String schemaTableName;
	
	public UserDataModel(DataSource dataSource,String schemaTableName) {
		setDataInitialization(dataSource, schemaTableName);
		
	}
	
	@Override
	public void setDataInitialization(DataSource dataSource,String schemaTableName) {
		this.dataSource = dataSource;
		this.schemaTableName = schemaTableName;
	}
	
	@Override
	public boolean create(User user) throws SQLException {
		String sql = "INSERT INTO "+schemaTableName+" (first_name, last_name, user_name, password, email)" 
				+ " VALUES (?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = dataSource.getConnection();
		statement = connection.prepareStatement(sql);
		
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getUserName());
		statement.setString(4, user.getPassword());
		statement.setString(5, user.getEmail());
		statement.executeUpdate();
		return true;
	}

	
	@Override
	public User get(String user,String pass) throws SQLException {
		
		String sql = "SELECT * FROM "+schemaTableName+" WHERE user_name='" + user + "' AND password='" + pass + "';";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = dataSource.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		if (resultSet.next()) {
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String userName = resultSet.getString("user_name");
			String password = resultSet.getString("password");
			String email = resultSet.getString("email");

			User recoveredUser = new User();
			recoveredUser.setFirstName(firstName);
			recoveredUser.setLastName(lastName);
			recoveredUser.setUserName(userName);
			recoveredUser.setPassword(password);
			recoveredUser.setEmail(email);
			return recoveredUser;
		} else {
			return null;
		}
	}

	@Override
	public List<User> getGroup(String table,String key,boolean exact) throws SQLException {
		//We don't want to retrieve a group of users
		return null;
	}
	
	@Override
	public boolean read(String user,String password) throws SQLException {
		String sql = "SELECT * FROM "+schemaTableName+" WHERE user_name='" + user + "' AND password='" + password + "';";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		connection = dataSource.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(String updateColumn, String updateValue,String column,String value) throws SQLException {
		//TODO access to change credentials
		String sql = "UPDATE "+schemaTableName+" SET "+updateColumn+"='"+updateValue+"' WHERE "+column+"="+value+";";
		Connection connection = null;
		Statement statement = null;

		connection = dataSource.getConnection();
		statement = connection.createStatement();
		if(statement.executeUpdate(sql)!=0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(String column,String value) throws SQLException {
		String sql = "DELETE FROM "+schemaTableName+" WHERE "+ column +"=" + value + ";";
		Connection connection = null;
		Statement statement = null;

		connection = dataSource.getConnection();
		statement = connection.createStatement();
		if(statement.executeUpdate(sql)!=0) {
			return true;
		}else {
			return false;
		}
	}

}
