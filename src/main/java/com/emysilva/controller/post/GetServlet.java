package com.emysilva.controller.post;

import com.emysilva.dao.post.PostDao;
import com.emysilva.model.post.LikeUnlike;
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
import java.util.List;

@WebServlet("/GetServlet")
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PostDao postDao;

	@Override
	public void init() throws ServletException {
		super.init();
	 postDao = new PostDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String action = request.getParameter("command");

			switch (action) {
				case "LOAD":
					loadPost(request, response);
					break;

				case "VIEW":
					viewPost(request, response);
					break;

				case "UPDATE":
					updatePost(request, response);
					break;

				case "DELETE":
					deletePost(request, response);
					break;
					
				case "LIKE":
					likePost(request, response);
					break;

				case "UNLIKE":
					unlikePost(request, response);

				default:
					listPosts(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void unlikePost(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String postId = request.getParameter("postId");

		postDao.addDislike(postId);

		// send to JSP page (view)
		listPosts(request, response);
	}

	private void likePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String postId = request.getParameter("postId");

		postDao.addLike(postId);

		// send to JSP page (view)
		listPosts(request, response);

	}

	private void viewPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String postId = request.getParameter("postId");

		Post getPost = postDao.getPost(postId);

		request.setAttribute("post", getPost);

		request.getRequestDispatcher("/WEB-INF/views/post/viewpost.jsp").forward(request, response);
	}

	private void listPosts(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// get posts from database
		List<Post> posts = postDao.listAllPosts();

		// add posts to the request
		request.setAttribute("posts", posts);

		// send to JSP page (view)
		response.sendRedirect("/list-posts");
	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read post id from form data
		String postId = request.getParameter("postId");

		// delete post from database
		postDao.deletePost(postId);

		// send them back to "list posts" page
		listPosts(request, response);
	}

	private void updatePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read post info from form data
		int id = Integer.parseInt(request.getParameter("postId"));
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String message = request.getParameter("message");
		String title = request.getParameter("title");

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = now.format(format);

		// create a new post object
		Post post = new Post(id, title, message, email, username, formatDateTime);

		request.setAttribute("post", post);

		System.out.println(post.toString());

		// perform update on database
		postDao.updatePost(post);

		// send them back to the "list posts" page
		listPosts(request, response);
	}

	private void loadPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read post id from form data
		String postId = request.getParameter("postId");

		// get post from database
		Post getPost = postDao.getPost(postId);

		// add post to the request
		request.setAttribute("post", getPost);

		// send them back to the "edit post" page
		request.getRequestDispatcher("/WEB-INF/views/post/editpost.jsp").forward(request, response);
	}
}
