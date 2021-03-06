package com.emysilva.controller.post;

import com.emysilva.dao.post.PostDao;
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

@WebServlet("/add-post")
public class AddServlet extends HttpServlet {

	PostDao postDao = new PostDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/post/addpost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//Copying all the input parameters in to local variables
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String message = request.getParameter("message");
		String title = request.getParameter("title");

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = now.format(format);

		//create a new object
		Post post = new Post(title, message, email, username, formatDateTime, 0, 0);

		try {
			//add the new object to the database
			postDao.addPost(post);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		// send them back to "list posts" page
		response.sendRedirect("/list-posts");

	}
}
