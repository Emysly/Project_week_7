package com.emysilva.controller.post;

import com.emysilva.dao.auth.UserDao;
import com.emysilva.model.auth.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-post")
public class AddServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/post/addpost.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Copying all the input parameters in to local variables

		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String message = request.getParameter("message");
		String title = request.getParameter("title");


		User userBean = new User();


		userBean.setEmail(email);
		userBean.setFirstname(username);
		userBean.setLastname(message);
		userBean.setUsername(title);

		UserDao userDao = new UserDao();

		//The core Logic of the Registration application is present here. We are going to insert user data in to the database.

		String userRegistered = null;
		try {
			userRegistered = userDao.registerUser(userBean);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (userRegistered != null) {
			if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
			{
				request.getRequestDispatcher("/WEB-INF/views/auth/userdashboard.jsp").forward(request, response);
			}
			else   //On Failure, display a meaningful message to the User.
			{
				request.setAttribute("errorMessage", userRegistered);
				request.getRequestDispatcher("/WEB-INF/views/post/addpost.jsp").forward(request, response);
			}
		}
	}
}
