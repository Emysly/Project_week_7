<%--
  Created by IntelliJ IDEA.
  User: emysilva
  Date: 29/08/2020
  Time: 12:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Update Post</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
<nav class="navbar navbar-light bg-primary">
	<span class="navbar-brand mb-0 h1 text-white">Facebook App</span>
	<div class="d-flex justify-content-end">
		<div>
			<a href="${pageContext.request.contextPath}/login" class="btn btn-outline-light bg-primary text-white">Log Out</a>
		</div>
	</div>
</nav>
<div class="container p-5">
	<h1 class="mt-5 text-center text-primary">Update Post</h1>
	<form action="GetServlet" method="GET" class="container px-5">
		<input type="hidden" name="command" value="UPDATE" />

		<input type="hidden" name="postId" value="${post.id}"/>

		<%
			String errorMessage;
			errorMessage = request.getAttribute("errorMessage") != null ? (String) request.getAttribute("errorMessage") : "";
		%>
		<p class="text-danger text-center"><%= errorMessage %></p>
		<label class="mt-5" for="email">Email</label>
		<input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${post.email}"/>

		<label class="mt-3" for="username">User Name</label>
		<input type="text" class="form-control" id="username" placeholder="Enter user name" name="username" value="${post.username}"/>

		<label class="mt-3" for="title">Title</label>
		<input type="text" class="form-control" id="title" placeholder="Enter post title" name="title" value="${post.title}"/>

		<label class="mt-3" for="message">Message</label>
		<textarea type="text" class="form-control" id="message" name="message">${post.message}</textarea>

		<input type="submit" class="btn btn-block btn-outline-primary p-2 mt-3" value="Send" />
	</form>
	<p class="mt-5">
		<a href="${pageContext.request.contextPath}/list-posts">Back to List</a>
	</p>
</div>
</body>
</html>
