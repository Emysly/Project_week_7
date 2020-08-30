<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Login form</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>
<body>
<div class="container p-5">
	<h1 class="mt-5 text-center text-secondary">Login</h1>
	<form action="<%= request.getContextPath() %>/login" method="post" class="container px-5">
		<%
			String loginErrorMessage;
			loginErrorMessage = request.getAttribute("loginErrorMessage") != null ? (String) request.getAttribute("loginErrorMessage") : "";
		%>
		<p class="text-danger"><%= loginErrorMessage %></p>

		<label class="mt-5" for="email">Email</label>
		<input type="text" class="form-control" id="email" placeholder="Enter email" name="email">

		<label class="mt-3" for="password">Password</label>
		<input type="password" class="form-control" id="password" placeholder="Enter password" name="password">

		<input type="submit" class="btn btn-block btn-outline-secondary p-2 mt-3" value="Login" />
	</form>
	<p class="m-5">
		not a member yet? <a href="${pageContext.request.contextPath}/register">Sign Up</a>
	</p>
</div>
</body>
</html>
