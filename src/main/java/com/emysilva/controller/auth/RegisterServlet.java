package com.emysilva.controller.auth;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	UserDao userDao;

	@Override
	public void init() throws ServletException {
		super.init();
		userDao = new UserDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
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
		String confPassword = request.getParameter("conf-password");
		String contact = request.getParameter("contact");

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = now.format(format);


		User userBean = new User(email, firstname, lastname, username, password, confPassword, contact, formatDateTime);


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
