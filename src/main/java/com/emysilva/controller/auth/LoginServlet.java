package com.emysilva.controller.auth;

import com.emysilva.dao.auth.UserDao;
import com.emysilva.dao.post.PostDao;
import com.emysilva.model.auth.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	UserDao userDao;

	@Override
	public void init() throws ServletException {
		super.init();
		userDao = new UserDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/auth/userlogin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		User user = new User();

		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));

		try {
			userDao.login(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//if user is a registered user or not
		if (user.isValid()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("currentSessionUser",user);
			response.sendRedirect("/list");
		} else {
			request.setAttribute("loginErrorMessage", "Invalid user or password");
			request.getRequestDispatcher("/WEB-INF/views/auth/userlogin.jsp").forward(request, response);
		}
	}

}
