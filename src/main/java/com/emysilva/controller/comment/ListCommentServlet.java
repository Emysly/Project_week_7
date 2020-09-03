package com.emysilva.controller.comment;

import com.emysilva.dao.comment.CommentDao;
import com.emysilva.model.comment.Comment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/list-comments")
public class ListCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//
	CommentDao commentDao = new CommentDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
//
//	@Resource(name="jdbc/FACEBOOK_MINI_CLONE")
//	private DataSource jdbcConnection;

//	@Override
//	public void init() throws ServletException {
//		super.init();
//		postDao = new PostDao(jdbcConnection);
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			listComments(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Comment> listComments = commentDao.listAllComments();

		request.setAttribute("listComments", listComments);

		request.getRequestDispatcher("/WEB-INF/views/comment/listcomments.jsp").forward(request, response);
	}
}
