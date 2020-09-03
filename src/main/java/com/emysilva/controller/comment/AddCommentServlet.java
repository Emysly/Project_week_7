package com.emysilva.controller.comment;

import com.emysilva.dao.comment.CommentDao;
import com.emysilva.dao.post.PostDao;
import com.emysilva.model.comment.Comment;
import com.emysilva.model.post.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {

	CommentDao commentDao = new CommentDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/comment/addcomment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//Copying all the input parameters in to local variables
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String message = request.getParameter("message");

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = now.format(format);

		//create a new object
		Comment comment = new Comment(email, message, username, formatDateTime, 0, 0);

		request.setAttribute("comment", comment);

		try {
			//add the new object to the database
			commentDao.addComment(comment);
		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		// send them back to "list views" page
		response.sendRedirect("/list-comments");

	}
}
