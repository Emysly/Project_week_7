package com.emysilva.dao.auth;

import com.emysilva.model.auth.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

	UserDao userDao = null;

	@BeforeEach
	void setUp() throws SQLException {
		userDao.connect();
		userDao = new UserDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void connect() {
	}

	@Test
	void disconnect() {
	}

	@Test
	void registerUser() throws SQLException, ClassNotFoundException {
		User user = new User();
		assertEquals("SUCCESS", userDao.registerUser(user));
	}

	@Test
	void login() {
	}
}