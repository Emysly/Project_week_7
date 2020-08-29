package com.emysilva.controller.post;

import com.emysilva.dao.post.PostDao;
import com.emysilva.model.post.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDao postDao;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		postDao = new PostDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertPost(request, response);
					break;
				case "/delete":
					deletePost(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updatePost(request, response);
					break;
				default:
					listPost(request, response);
					break;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		List<Post> listPosts = postDao.listAllPosts();
		request.setAttribute("listPosts", listPosts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listposts.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addpost.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		Post existingBook = postDao.getPost(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editpost.jsp");
		request.setAttribute("post", existingBook);
		dispatcher.forward(request, response);

	}

	private void insertPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException {
		String title = request.getParameter("title");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String message = request.getParameter("message");

		Post newPost = new Post(title, message, email, username);
		PostDao.addPost(newPost);
		response.sendRedirect("listposts");
	}

	private void updatePost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String message = request.getParameter("message");

		Post post = new Post(id, title, message, email, username);
		postDao.updatePost(post);
		response.sendRedirect("listposts");
	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));

		Post post = new Post(id);
		postDao.deletePost(post);
		response.sendRedirect("listposts");

	}
}
