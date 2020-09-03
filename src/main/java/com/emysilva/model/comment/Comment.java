package com.emysilva.model.comment;

import java.util.Date;

public class Comment {
	private int id;
	private int post_id;
	private String email;
	private String username;
	private String message;
	private String createdAt;
	private int likePost;
	private int dislikePost;


	public Comment(int comment_id, int post_id, String email, String username, String message, String createdAt, int likePost, int dislikePost) {
		this.id = comment_id;
		this.post_id = post_id;
		this.email = email;
		this.username = username;
		this.message = message;
		this.createdAt = createdAt;
		this.likePost = likePost;
		this.dislikePost = dislikePost;
	}
	public Comment(int id, String email, String message, String username, String createdAt, int like, int unlike) {
		this.id = id;
		this.email = email;
		this.message = message;
		this.username = username;
		this.createdAt = createdAt;
		likePost = like;
		dislikePost = unlike;
	}

	public Comment(String email, String message, String username) {
		this.email = email;
		this.message = message;
		this.username = username;
	}

	public Comment(String email, String message, String username, String createdAt, int likes, int dislikes) {
		this.id = id;
		this.email = email;
		this.message = message;
		this.username = username;
		this.createdAt = createdAt;
		likePost = likes;
		dislikePost = dislikes;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public int getLikePost() {
		return likePost;
	}

	public void setLikePost(int likePost) {
		this.likePost = likePost;
	}

	public int getDislikePost() {
		return dislikePost;
	}

	public void setDislikePost(int dislikePost) {
		this.dislikePost = dislikePost;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", message='" + message + '\'' +
				", createdAt='" + createdAt + '\'' +
				", likePost=" + likePost +
				", dislikePost=" + dislikePost +
				'}';
	}
}
