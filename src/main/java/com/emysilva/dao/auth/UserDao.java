package com.emysilva.dao.auth;

import com.emysilva.model.auth.User;

import java.sql.*;

public class UserDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public UserDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public UserDao() {

	}

	//set up connection of the database
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
					jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	//set up disconnection of the database
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}


	public String registerUser(User userbean) throws ClassNotFoundException {
		String email, firstname, lastname, username, password, confpassword,
				contact;
		if (!userbean.getEmail().equals("")) {
			email = userbean.getEmail();
		} else {
			return "User email must be provided";
		}
		if (!userbean.getFirstname().equals("")) {
			firstname = userbean.getFirstname();
		} else {
			return "User firstname must be provided";
		}

		if (!userbean.getLastname().equals("")) {
			lastname = userbean.getLastname();
		} else {
			return "User lastname must be provided";
		}

		if (!userbean.getUsername().equals("")) {
			username = userbean.getUsername();
		} else {
			return "User username must be provided";
		}

		if (!userbean.getPassword().equals("")) {
			password = userbean.getPassword();
		} else {
			return "User password must be provided";
		}


		if (!userbean.getConfpassword().equals("")) {
			confpassword = userbean.getConfpassword();
		} else {
			return "User should confirm their password";
		}

		if (!userbean.getPassword().equals(userbean.getConfpassword()))
			return "User password must match";

		if (!userbean.getContact().equals("")) {
			contact = userbean.getContact();
		} else {
			return "User contact must be provided";
		}


			try
			{
				connect();

				//Insert user details into the table 'user'
				String query = "insert into user(email, firstname, lastname, username, password, confpassword, contact) values (?, ?, ?, ?, ?, ?, ?)";

				//Making use of prepared statements here to insert bunch of data
				PreparedStatement preparedStatement = jdbcConnection.prepareStatement(query);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, firstname);
				preparedStatement.setString(3, lastname);
				preparedStatement.setString(4, username);
				preparedStatement.setString(5, password);
				preparedStatement.setString(6, confpassword);
				preparedStatement.setString(7, contact);

				int i= preparedStatement.executeUpdate();
				System.out.println(i);

				//Just to ensure data has been inserted into the database
				if (i!=0)
					return "SUCCESS";
//				errorMessage = "Oops.. User details already exist..!"; // On failure, send a message from here.
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return "Oops.. User details already exist..!";

	}

	public String login(User user) throws ClassNotFoundException, SQLException {

		//preparing some objects for connection
		Statement statement = null;
		ResultSet resultSet = null;

		String email = user.getEmail();
		String password = user.getPassword();

		String searchQuery =
				"select * from user where email='"
						+ email
						+ "' AND password='"
						+ password
						+ "'";

		// "System.out.println" prints in the console; Normally used to trace the process
		System.out.println("Your user name is " + email);
		System.out.println("Your password is " + password);
		System.out.println("Query: "+searchQuery);

		try
		{
			//connect to DB
			connect();

			//create a statement
			statement = jdbcConnection.createStatement();

			//execute query
			resultSet = statement.executeQuery(searchQuery);

			if(resultSet.next())
			{
				//checks if the email and password is valid
				user.setValid((email.equalsIgnoreCase(resultSet.getString("email")))
						&& (password.equals(resultSet.getString("password"))));

			// if user does not exist set the isValid variable to false
//			if (!more)
//			{
//				user.setValid(false);
//				return "Sorry, you are not a registered user! Please sign up first";
//			}
//
//			//if user exists set the isValid variable to true
//			else
//			{
//				String firstName = rs.getString("FirstName");
//				String lastName = rs.getString("LastName");
//
//				System.out.println("Welcome " + firstName);
//				user.setFirstname(firstName);
//				user.setLastname(lastName);
//				user.setValid(true);
			}
		}

		//some exception handling
		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		//close all the objects for connection
		finally {
			close(resultSet, statement);

		}
		return "";

	}

	private void close(ResultSet resultSet, Statement statement) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (Exception throwables) {
			throwables.printStackTrace();
		}
	}

}
