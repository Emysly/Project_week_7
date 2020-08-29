package com.emysilva.dao.auth;

import com.emysilva.model.auth.User;

import java.sql.*;

public class UserDao {
	public UserDao() {}

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

			Connection con;
			PreparedStatement preparedStatement;

			Class.forName("com.mysql.cj.jdbc.Driver");

			try
			{
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");

				String query = "insert into user(email, firstname, lastname, username, password, confpassword, contact) values (?, ?, ?, ?, ?, ?, ?)"; //Insert user details into the table 'USERS'
				preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, firstname);
				preparedStatement.setString(3, lastname);
				preparedStatement.setString(4, username);
				preparedStatement.setString(5, password);
				preparedStatement.setString(6, confpassword);
				preparedStatement.setString(7, contact);

				int i= preparedStatement.executeUpdate();
				System.out.println(i);
				if (i!=0)  //Just to ensure data has been inserted into the database
					return "SUCCESS";
//				errorMessage = "Oops.. User details already exist..!"; // On failure, send a message from here.
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return "Oops.. User details already exist..!";

	}

	public static String login(User user) throws ClassNotFoundException, SQLException {

		//preparing some objects for connection
		Statement stmt = null;
		Connection currentCon = null;
		ResultSet rs = null;

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

		Class.forName("com.mysql.cj.jdbc.Driver");

		try
		{
			//connect to DB
			currentCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
//			boolean more = rs.next();

			if(rs.next())
			{
				user.setValid((email.equals(rs.getString("email")))
						&& (password.equals(rs.getString("password"))));

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

		catch (Exception ex)
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		//some exception handling
		finally {
			if (rs != null)	{
				try {
					rs.close();
				} catch (Exception ignored) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception ignored) {}
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception ignored) {
				}
			}
		}
		return "";

	}

}
