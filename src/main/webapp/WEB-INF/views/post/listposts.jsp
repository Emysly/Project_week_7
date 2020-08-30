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
<%--	<div>--%>
<%--		<a href="/new">Add New Post</a>--%>
<%--		&nbsp;&nbsp;&nbsp;--%>
<%--		<a href="/list">List All Posts</a>--%>
<%--	</div>--%>
	<div>
		<a href="${pageContext.request.contextPath}/add-post" class="btn btn-outline-success m-3">Add Post</a>
		<a href="${pageContext.request.contextPath}/list-posts" class="btn btn-outline-info m-3">List All Post</a>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col" style="width: 5%">Id</th>
					<th scope="col" style="width: 60%">Title</th>
					<th scope="col">Creator</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<c:forEach var="post" items="${listPosts}">

				<c:url var="updateLink" value="GetServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<c:url var="viewLink" value="GetServlet">
					<c:param name="command" value="VIEW" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<!--  set up a link to delete a student -->
				<c:url var="deleteLink" value="GetServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<tr>
					<td>${post.id}</td>
					<td>${post.title}</td>
					<td>${post.username}</td>
					<td>
						<a href="${updateLink}">Update</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${viewLink}">View</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${deleteLink}"
						   onclick="if (!(confirm('Are you sure you want to delete this post?'))) return false">
							Delete</a>
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