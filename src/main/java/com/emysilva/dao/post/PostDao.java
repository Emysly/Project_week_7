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

	public PostDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public PostDao() {

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


	public boolean addPost(Post post) throws SQLException {
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

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			//connect to database
			connect();

			String sql = "SELECT * FROM post";

			//create statement
			statement = jdbcConnection.createStatement();

			//execute query
			resultSet = statement.executeQuery(sql);

			//get all data from the database
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String email = resultSet.getString("email");
				String title = resultSet.getString("title");
				String username = resultSet.getString("username");
				String message = resultSet.getString("message");

				//create a new object with the data
				Post post = new Post(id, title, message, email, username);

				//add the object to the list of objects
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

	public void deletePost(String id) throws SQLException {
		PreparedStatement preparedStatement = null;

		try {

		int postId = Integer.parseInt(id);

		connect();

		String sql = "DELETE FROM post where id = ?";


		preparedStatement = jdbcConnection.prepareStatement(sql);
		preparedStatement.setInt(1, postId);

		preparedStatement.execute();

		} finally {
			close(null, preparedStatement);
		}
	}

	public void updatePost(Post post) throws SQLException {
		PreparedStatement statement = null;
		try {
			connect();

			String sql = "UPDATE post SET email = ?, title = ?, username = ?, message = ? WHERE id = ?";

			statement = jdbcConnection.prepareStatement(sql);

			//add the updated data to database
			statement.setString(1, post.getTitle());
			statement.setString(2, post.getMessage());
			statement.setString(3, post.getEmail());
			statement.setString(4, post.getUsername());
			statement.setInt(5, post.getId());

			statement.execute();

			System.out.println(post.toString());
		} finally {
			close(null, statement);
		}

	}


	public Post getPost(String id) throws Exception {

		Post post;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String sql = "SELECT * FROM post WHERE id = ?";

			connect();

			int postId = Integer.parseInt(id);

			statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, postId);

			resultSet = statement.executeQuery();

			//get all the data associated to the post that is found
			if (resultSet.next()) {
				String title = resultSet.getString("title");
				String email = resultSet.getString("email");
				String username = resultSet.getString("username");
				String message = resultSet.getString("message");

				//create a new object with the data
				post = new Post(postId, title, message, email, username);
			} else {
				throw new Exception("Could not find post with id: " + postId);
			}
			return post;
		} finally {
			close(resultSet, statement);
		}
	}

	public void addLike(Post post) throws SQLException {

		int like = post.getLikePost();

		post.setLikePost(like + 1);

	}

	public void addDislike(Post post) throws SQLException {

		int dislike = post.getDislikePost();

		post.setDislikePost(dislike + 1);

	}



}
