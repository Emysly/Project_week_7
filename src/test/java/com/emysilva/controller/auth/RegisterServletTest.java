package com.emysilva.controller.auth;

import com.emysilva.dao.auth.UserDao;
import com.emysilva.model.auth.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServletTest {

	UserDao userDao = null;

	@BeforeEach
	void setUp() {
//		userDao.connect();
		userDao = new UserDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");

	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void init() {
	}

	@Test
	void doGet() {
	}

	@Test
	void doPost() {

	}
}