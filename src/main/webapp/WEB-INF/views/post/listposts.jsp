<%@ page import="com.emysilva.model.post.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
	<%
		List<Post> posts =
				(List<Post>) request.getAttribute("listPosts");
	%>
<body>
<div class="alert alert-success alert-dismissible fade show text-center" role="alert">
	<strong>User login successfully</strong>
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<div class="container mt-5">
	<div>
		<h1>List Posts</h1>
	</div>
<%--	<div>--%>
<%--		<a href="/new">Add New Post</a>--%>
<%--		&nbsp;&nbsp;&nbsp;--%>
<%--		<a href="/list">List All Posts</a>--%>
<%--	</div>--%>
	<div>
		<button type="button" class="btn btn-outline-success m-3"><a href="${pageContext.request.contextPath}/add-post">Add Post</a></button>
		<button type="button" class="btn btn-outline-info m-3"><a href="${pageContext.request.contextPath}/list-posts">List All Post</a></button>
		<table class="table table-hover">
			<thead>
			<tr>
				<th scope="col" style="width: 5%">Id</th>
				<th scope="col" style="width: 60%">Title</th>
				<th scope="col">Creator</th>
				<th scope="col">Actions</th>
			</tr>
			</thead>
			<% for (Post post: posts) { %>
					<tr>
						<td><%= post.getId() %></td>
						<td><%= post.getTitle() %></td>
						<td><%= post.getUsername() %></td>
						<td>
							<a href="/edit?id=<%= post.getId() %>">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="/delete?id=<%= post.getId() %>">Delete</a>
						</td>
					</tr>
			<% } %>

		</table>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>