package com.emysilva.controller.auth;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emysilva.dao.auth.UserDao;
import com.emysilva.model.auth.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
		new UserDao();
	}

	public RegisterServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/auth/userregister.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Copying all the input parameters in to local variables

		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confpassword = request.getParameter("conf-password");
		String contact = request.getParameter("contact");


		User userBean = new User();


		userBean.setEmail(email);
		userBean.setFirstname(firstname);
		userBean.setLastname(lastname);
		userBean.setUsername(username);
		userBean.setPassword(password);
		userBean.setConfpassword(confpassword);
		userBean.setContact(contact);

		UserDao userDao = new UserDao();

		//The core Logic of the Registration application is present here. We are going to insert user data in to the database.

		String userRegistered = null;
		try {
			userRegistered = userDao.registerUser(userBean);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (userRegistered != null) {
			if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
			{
				request.getRequestDispatcher("/WEB-INF/views/auth/userlogin.jsp").forward(request, response);
			}
			else   //On Failure, display a meaningful message to the User.
			{
				request.setAttribute("errorMessage", userRegistered);
				request.getRequestDispatcher("/WEB-INF/views/auth/userregister.jsp").forward(request, response);
			}
		}
	}








//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		String firstName = request.getParameter("firstname");
//		String lastName = request.getParameter("lastname");
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String contact = request.getParameter("contact");
//
//		User user = new User();
//		user.setFirstname(firstName);
//		user.setLastname(lastName);
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setContact(contact);
//
//		try {
//			userDao.registerUser(user);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		response.sendRedirect("userlogin.jsp");
//	}
}
