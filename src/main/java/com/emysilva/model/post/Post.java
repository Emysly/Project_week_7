package com.emysilva.model.post;

public class Post {
	private int id;
	private String title;
	private String message;
	private String email;
	private String username;

	public Post(String title, String message, String email, String username) {
		this.title = title;
		this.message = message;
		this.email = email;
		this.username = username;
	}

	public Post(int id, String title, String message, String email, String username) {
		this.id = id;
		this.title = title;
		this.message = message;
		this.email = email;
		this.username = username;
	}

	public Post(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
