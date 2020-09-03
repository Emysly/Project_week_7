package com.emysilva.controller.comment;

import com.emysilva.dao.comment.CommentDao;
import com.emysilva.dao.post.PostDao;
import com.emysilva.model.comment.Comment;
import com.emysilva.model.post.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	CommentDao commentDao;

	@Override
	public void init() throws ServletException {
		super.init();
		commentDao = new CommentDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			try {
				// read the "command" parameter
				String action = request.getParameter("command");


				// route to the appropriate method
				switch (action) {

					case "LIST":
						listComments(request, response);
						break;

					case "ADD":
						addComment(request, response);
						break;

					case "LOAD":
						loadComment(request, response);
						break;

					case "VIEW":
						getComment(request, response);

					case "UPDATE":
						updateComment(request, response);
						break;

					case "DELETE":
						deleteComment(request, response);
						break;
						
					case "LIKE":
						likeComment(request, response);
						break;
						
					case "UNLIKE":
						unlikeComment(request, response);
						break;

					default:
						listComments(request, response);
				}

			} catch (Exception exc) {
				throw new ServletException(exc);
			}

		}

	private void likeComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String commentId = request.getParameter("commentId");

		commentDao.addLikeComment(commentId);

		// send to JSP page (view)
		listComments(request, response);
	}

	private void unlikeComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String commentId = request.getParameter("commentId");

		commentDao.addDislikeComment(commentId);

		// send to JSP page (view)
		listComments(request, response);
	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

			// read comment id from form data
			String commentId = request.getParameter("commentId");

			// delete comment from database
			commentDao.deleteComment(commentId);

			// send them back to "list comments" page
			listComments(request, response);
		}

		private void updateComment(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

			// read comment info from form data
			int id = Integer.parseInt(request.getParameter("studentId"));
			String email = request.getParameter("email");
			String message = request.getParameter("message");
			String username = request.getParameter("username");

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String formatDateTime = now.format(format);

			// create a new comment object
			Comment comment = new Comment(id, email, message, username, formatDateTime, 0, 0);

			// place comment in the request attribute
			request.setAttribute("comment", comment);

			// perform update on database
			commentDao.updateComment(comment);

			// send them back to the "list comments" page
			listComments(request, response);

		}

		private void getComment(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

			// read comment id from form data
			String commentId = request.getParameter("commentId");

			// get comment from database (db util)
			Comment comment = commentDao.getComment(commentId);

			request.setAttribute("comment", comment);

			// send to jsp page: listcomments.jsp
			listComments(request, response);
		}

	private void loadComment(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// read comment id from form data
		String commentId = request.getParameter("commentId");

		// get comment from database (db util)
		Comment comment = commentDao.getComment(commentId);

		request.setAttribute("comment", comment);

		// send to jsp page: updatecomment.jsp
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/views/comment/updatecomment.jsp");
		dispatcher.forward(request, response);
	}

		private void addComment(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// read comment info from form data
			String email = request.getParameter("email");
			String message = request.getParameter("message");
			String username = request.getParameter("username");

			// create a new comment object
			Comment comment = new Comment(email, message, username);

			// add the comment to the database
			commentDao.addComment(comment);

			// send back to main page (the comments list)
			listComments(request, response);
		}

		private void listComments(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

			// get comments from database
			List<Comment> comments = commentDao.listAllComments();

			// add comments to the request
			request.setAttribute("comments", comments);

			// send to JSP page (view)
			response.sendRedirect("/list-comments");
		}

	}

