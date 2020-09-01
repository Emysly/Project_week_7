package com.emysilva.dao.post;

import com.emysilva.model.post.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {

	PostDao postDao = null;

	@BeforeEach
	void setUp() throws ClassNotFoundException {
		connect();
		postDao = new PostDao("jdbc:mysql://localhost:3306/facebookclone?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "swag4sure");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void connect() {
	}

	@Test
	void addPost() {
	}

	@Test
	void listAllPosts() {
	}

	@Test
	void deletePost() {
	}

	@Test
	void updatePost() {
	}

	@Test
	void getPost() {
	}

	@Test
	void addLike() {
	}

	@Test
	void addDislike() {
	}
}