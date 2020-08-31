package com.emysilva.controller.post;

import com.emysilva.dao.post.PostDao;
import com.emysilva.model.post.Post;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/list-posts")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//
	PostDao postDao = new PostDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
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
			listPosts(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listPosts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Post> listPosts = postDao.listAllPosts();

		request.setAttribute("listPosts", listPosts);

		request.getRequestDispatcher("/WEB-INF/views/post/listposts.jsp").forward(request, response);
	}
}
