package com.emysilva.controller.post;

import com.emysilva.dao.auth.UserDao;
import com.emysilva.dao.post.PostDao;
import com.emysilva.model.auth.User;
import com.emysilva.model.post.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add-post")
public class AddServlet extends HttpServlet {

	PostDao postDao = new PostDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/post/addpost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//Copying all the input parameters in to local variables

		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String message = request.getParameter("message");
		String title = request.getParameter("title");


		Post post = new Post(title, message, email, username);

		try {
			postDao.addPost(post);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

			response.sendRedirect("/list");

	}
}
