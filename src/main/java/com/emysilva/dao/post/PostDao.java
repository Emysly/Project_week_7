package com.emysilva.dao.post;

import com.emysilva.model.post.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public PostDao() {}

	public PostDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}



	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
					jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public void addPost(Post post) throws SQLException {
		String email, username, title, message;

		if (!post.getEmail().equals("")) {
			email = post.getEmail();
		} else {
			return;
		}
		if (!post.getUsername().equals("")) {
			username = post.getUsername();
		} else {
			return;
		}

		if (!post.getTitle().equals("")) {
			title = post.getTitle();
		} else {
			return;
		}

		if (!post.getMessage().equals("")) {
			message = post.getMessage();
		} else {
			return;
		}


		PreparedStatement preparedStatement;

			connect();

			String query = "insert into post(email, title, message, username) values (?, ?, ?, ?)"; //Insert post details into the table 'post'
			preparedStatement = jdbcConnection.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, message);
			preparedStatement.setString(4, username);

			//Just to ensure data has been inserted into the database
		}

	public List<Post> listAllPosts() throws SQLException {
		List<Post> listPost = new ArrayList<>();

		String sql = "SELECT * FROM post";

		connect();
		Statement statement = jdbcConnection.createStatement();
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

		disconnect();

		return listPost;
	}

	public void deletePost(Post post) throws SQLException {
		String sql = "DELETE FROM post where id = ?";

		connect();

		PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql);
		preparedStatement.setString(1, post.getEmail());

		preparedStatement.close();
		disconnect();
	}

	public void updatePost(Post post) throws SQLException {
		String sql = "UPDATE post SET email = ?, title = ?, username = ?, message = ? WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, post.getTitle());
		statement.setString(2, post.getUsername());
		statement.setString(3, post.getMessage());

		statement.close();
		disconnect();
	}

	public Post getPost(int id) throws SQLException {
		Post post = null;
		String sql = "SELECT * FROM post WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
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

