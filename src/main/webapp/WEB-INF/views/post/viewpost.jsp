<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>${post.title}</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
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
<div class="container mt-5">
	<h1 class="mt-5 text-center">${post.title}</h1>
	<div class="d-flex justify-content-between">
	<span>posted by: <span class="text-info"> ${post.username}</span></span>

		<a href="${pageContext.request.contextPath}/list-comments" class="btn btn-outline-success m-3"><i class="fas fa-eye"></i>View Comment</a>
	</div>
	<hr>
	<br>
	<h4>${post.message}</h4>
	<p class="mt-5">
		<a href="${pageContext.request.contextPath}/list-posts">Back to List</a>
	</p>
</div>
</body>
</html>
