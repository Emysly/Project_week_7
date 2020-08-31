package com.emysilva.model.comment;

import java.util.Date;

public class Comment {
	private int id;
	private String email;
	private String username;
	private String message;
//	private String createdAt = new Date().toString();
//	private int likes = 0;
//	private int dislikes = 0;


	public Comment(int id, String email, String message, String username) {
		this.id = id;
		this.email = email;
		this.message = message;
		this.username = username;
	}

	public Comment(String email, String message, String username) {
		this.email = email;
		this.message = message;
		this.username = username;
	}

//	public Comment(int id, String email, String message, String username, String createdAt, int likes, int dislikes) {
//		this.id = id;
//		this.email = email;
//		this.message = message;
//		this.username = username;
//		this.createdAt = createdAt;
//		this.likes = likes;
//		this.dislikes = dislikes;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(String createdAt) {
//		this.createdAt = createdAt;
//	}
//
//
//	public int getLikes() {
//		return likes;
//	}
//
//	public void setLikes(int likes) {
//		this.likes = likes;
//
//	}
//
//	public int getDislikes() {
//		return dislikes;
//	}
//
//	public void setDislikes(int dislikes) {
//		this.dislikes = dislikes;
//	}


	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
