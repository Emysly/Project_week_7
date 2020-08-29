package com.emysilva.dao.post;

import com.emysilva.model.post.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;

	public PostDao() {}

	public PostDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public static String addPost(Post post) throws ClassNotFoundException {
		String email, username, title, message;

		if (!post.getEmail().equals("")) {
			email = post.getEmail();
		} else {
			return "Creator email must be provided";
		}
		if (!post.getUsername().equals("")) {
			username = post.getUsername();
		} else {
			return "Creator username must be provided";
		}

		if (!post.getTitle().equals("")) {
			title = post.getTitle();
		} else {
			return "Post title must be provided";
		}

		if (!post.getMessage().equals("")) {
			message = post.getMessage();
		} else {
			return "Post message must be provided";
		}


		Connection con;
		PreparedStatement preparedStatement;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");

			String query = "insert into post(email, title, message, username) values (?, ?, ?, ?)"; //Insert post details into the table 'post'
			preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, message);
			preparedStatement.setString(4, username);

			int i= preparedStatement.executeUpdate();

			if (i!=0)  //Just to ensure data has been inserted into the database
				return "SUCCESS";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "";

	}

	public List<Post> listAllPosts() throws SQLException, ClassNotFoundException {
		List<Post> listPost = new ArrayList<>();

		String sql = "SELECT * FROM post";

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String email = resultSet.getString("email");
			String title = resultSet.getString("title");
			String username = resultSet.getString("username");
			String message = resultSet.getString("message");

			Post post = new Post(title, message, email, username);
			listPost.add(post);
		}

		resultSet.close();
		statement.close();

//		disconnect();

		return listPost;
	}

	public boolean deletePost(Post post) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM post where id = ?";

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");

		Statement statement = con.createStatement();

		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, post.getEmail());

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		statement.close();
//		disconnect();
		return rowDeleted;
	}

	public boolean updatePost(Post post) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE post SET email = ?, title = ?, username = ?, message = ? WHERE id = ?";

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");


		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, post.getTitle());
		statement.setString(2, post.getUsername());
		statement.setString(3, post.getMessage());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
//		disconnect();
		return rowUpdated;
	}

	public Post getPost(int id) throws SQLException, ClassNotFoundException {
		Post post = null;
		String sql = "SELECT * FROM post WHERE id = ?";

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");


		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String title = resultSet.getString("title");
			String email = resultSet.getString("email");
			String username = resultSet.getString("username");
			String message = resultSet.getString("message");

			post = new Post(id, title, email, username, message);
		}

		resultSet.close();
		statement.close();

		return post;
	}

}

