<%@ page import="java.util.List" %>
<%@ page import="com.emysilva.model.post.Post" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div>
		<a href="/new">Add New Post</a>
		&nbsp;&nbsp;&nbsp;
		<a href="/list">List All Posts</a>
	</div>
	<div>
		<button type="button" class="btn btn-outline-success m-3"><a href="${pageContext.request.contextPath}/add-post">Add Post</a></button>
		<table class="table table-hover">
			<thead>
			<tr>
				<th scope="col" width="5%">Id</th>
				<th scope="col" width="60%">Title</th>
				<th scope="col">Creator</th>
				<th>Actions</th>
			</tr>
			</thead>

			<c:forEach var="book" items="${posts}">
				<tr>
					<td><c:out value="${post.id}" /></td>
					<td><c:out value="${post.title}" /></td>
					<td><c:out value="${post.username}" /></td>
					<td>
						<a href="/edit?id=<c:out value='${post.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/delete?id=<c:out value='${post.id}' />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>