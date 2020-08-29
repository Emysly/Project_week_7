package com.emysilva.dao.post;

import com.emysilva.model.auth.User;
import com.emysilva.model.post.Post;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public PostDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public PostDao() {

	}

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

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

//	public PostDao(St jdbcConnection) {
//		this.jdbcConnection = jdbcConnection;
//	}



//	protected void connect() throws SQLException {
//		if (jdbcConnection == null || jdbcConnection.isClosed()) {
//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//			} catch (ClassNotFoundException e) {
//				throw new SQLException(e);
//			}
//			jdbcConnection = DriverManager.getConnection(
//					jdbcURL, jdbcUsername, jdbcPassword);
//		}
//	}
//
//	protected void disconnect() throws SQLException {
//		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
//			jdbcConnection.close();
//		}
//	}


	public boolean addPost(Post post) throws SQLException, ClassNotFoundException {
		String email, username, title, message;

		email = post.getEmail();
		username = post.getUsername();
		title = post.getTitle();
		message = post.getMessage();


//		if (!post.getEmail().equals("")) {
//
//		} else {
//			return "Creator email must be provided";
//		}
//		if (!post.getUsername().equals("")) {
//		} else {
//			return "Creator username must be provided";
//		}
//
//		if (!post.getTitle().equals("")) {
//		} else {
//			return "Post title must be provided";
//		}
//
//		if (!post.getMessage().equals("")) {
//
//		} else {
//			return "Post message must be provided";
//		}

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");

			String query = "insert into post(email, title, message, username) values (?, ?, ?, ?)"; //Insert user details into the table 'USERS'
//			connect();

			PreparedStatement preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, message);
			preparedStatement.setString(4, username);

//			System.out.println(i);
//			preparedStatement.close();
//			if (i > 0)  //Just to ensure data has been inserted into the database
//				return "SUCCESS";
//				errorMessage = "Oops.. User details already exist..!"; // On failure, send a message from here.
//			return "";
			boolean rowInserted = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
			disconnect();
			return rowInserted;
	}
//		return "Oops.. Post details already exist..!";


	public List<Post> listAllPosts() throws SQLException, ClassNotFoundException {
		List<Post> listPost = new ArrayList<>();

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

//		Class.forName("com.mysql.cj.jdbc.Driver");

		try {

			connect();

			String sql = "SELECT * FROM post";

			statement = jdbcConnection.createStatement();

			resultSet = statement.executeQuery(sql);


			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String email = resultSet.getString("email");
				String title = resultSet.getString("title");
				String username = resultSet.getString("username");
				String message = resultSet.getString("message");

				Post post = new Post(id, title, message, email, username);

				listPost.add(post);
			}

			return listPost;


		} finally {
			close(resultSet, statement);
		}
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

//	public void deletePost(Post post) throws SQLException {
//		String sql = "DELETE FROM post where id = ?";
//
//		connect();
//
//		PreparedStatement preparedStatement = jdbcConnection.prepareStatement(sql);
//		preparedStatement.setString(1, post.getEmail());
//
//		preparedStatement.close();
//		disconnect();
//	}
//
//	public void updatePost(Post post) throws SQLException {
//		String sql = "UPDATE post SET email = ?, title = ?, username = ?, message = ? WHERE id = ?";
//
//		connect();
//
//		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//		statement.setString(1, post.getTitle());
//		statement.setString(2, post.getUsername());
//		statement.setString(3, post.getMessage());
//
//		statement.close();
//		disconnect();
//	}
//
//	public Post getPost(int id) throws SQLException {
//		Post post = null;
//		String sql = "SELECT * FROM post WHERE id = ?";
//
//		connect();
//
//		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//		statement.setInt(1, id);
//
//		ResultSet resultSet = statement.executeQuery();
//
//		if (resultSet.next()) {
//			String title = resultSet.getString("title");
//			String email = resultSet.getString("email");
//			String username = resultSet.getString("username");
//			String message = resultSet.getString("message");
//
////			post = new Post(id, title, email, username, message);
//		}
//
//		resultSet.close();
//		statement.close();
//
//		return post;
//	}

}
