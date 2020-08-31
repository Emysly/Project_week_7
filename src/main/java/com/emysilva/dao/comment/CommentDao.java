package com.emysilva.dao.comment;

import com.emysilva.model.comment.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDao {
		private String jdbcURL;
		private String jdbcUsername;
		private String jdbcPassword;
		private Connection jdbcConnection;

		public CommentDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
			this.jdbcURL = jdbcURL;
			this.jdbcUsername = jdbcUsername;
			this.jdbcPassword = jdbcPassword;
		}

		public CommentDao() {

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


		public boolean addComment(Comment comment) throws SQLException, ClassNotFoundException {
			String email, username, message, createdAt;

			email = comment.getEmail();
			username = comment.getUsername();
			message = comment.getMessage();
//			createdAt = comment.getCreatedAt();
//			int likes = comment.getLikes();
//			int dislikes = comment.getDislikes();

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");

			String query = "insert into comment(email, message, username) values (?, ?, ?)"; //Insert user details into the table 'comment'


			PreparedStatement preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, message);
			preparedStatement.setString(3, username);
//			preparedStatement.setString(4, createdAt);
//			preparedStatement.setInt(5, likes);
//			preparedStatement.setInt(6, dislikes);

//			System.out.println(i);
//			preparedStatement.close();
//			if (i > 0)  //Just to ensure data has been inserted into the database
//				return "SUCCESS";
//				errorMessage = "Oops.. User details already exist..!"; // On failure, send a message from here.
//			return "";
			boolean rowInserted = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
//			disconnect();
			return rowInserted;
		}
//		return "Oops.. Post details already exist..!";


		public List<Comment> listAllComments() throws SQLException {
			List<Comment> listComment = new ArrayList<>();

			Statement statement = null;
			ResultSet resultSet = null;

			try {

				connect();

				String sql = "SELECT * FROM comment";

				statement = jdbcConnection.createStatement();

				resultSet = statement.executeQuery(sql);


				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String email = resultSet.getString("email");
					String message = resultSet.getString("message");
					String username = resultSet.getString("username");
//					String date = resultSet.getString("createdAt");
//					int likes = resultSet.getInt("likes");
//					int dislikes = resultSet.getInt("dislikes");

					Comment comment = new Comment(id, email, message, username);

					listComment.add(comment);
				}

				return listComment;


			} finally {
				close(resultSet, statement);
			}
		}

		public void deleteComment(String id) throws SQLException {
			PreparedStatement preparedStatement = null;

			try {

				int commentId = Integer.parseInt(id);

				connect();

				String sql = "DELETE FROM comment where id = ?";


				preparedStatement = jdbcConnection.prepareStatement(sql);
				preparedStatement.setInt(1, commentId);

				preparedStatement.execute();

			} finally {
				close(null, preparedStatement);
			}
		}

		public void updateComment(Comment comment) throws SQLException {
			PreparedStatement statement = null;
			try {
				connect();

				String sql = "UPDATE comment SET email = ?, message = ?, username = ? WHERE id = ?";

				statement = jdbcConnection.prepareStatement(sql);

				statement.setString(1, comment.getEmail());
				statement.setString(2, comment.getMessage());
				statement.setString(3, comment.getUsername());
				statement.setInt(4, comment.getId());

				statement.execute();

				System.out.println(comment.toString());
			} finally {
				close(null, statement);
			}

		}


		public Comment getComment(String id) throws Exception {

			Comment comment;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			try {
				String sql = "SELECT * FROM comment WHERE id = ?";

				connect();

				int commentId = Integer.parseInt(id);

				statement = jdbcConnection.prepareStatement(sql);
				statement.setInt(1, commentId);

				resultSet = statement.executeQuery();

				if (resultSet.next()) {
					String email = resultSet.getString("email");
					String message = resultSet.getString("message");
					String username = resultSet.getString("username");

					comment = new Comment(commentId, email, message, username);
				} else {
					throw new Exception("Could not find comment with id: " + commentId);
				}
				return comment;
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

}
