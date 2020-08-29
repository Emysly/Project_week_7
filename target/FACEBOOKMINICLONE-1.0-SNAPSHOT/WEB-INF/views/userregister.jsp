<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Registration form</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>
<body>
 <div class="container p-5">
  <h1 class="mt-5 text-center text-secondary">Register</h1>
  <form action="<%= request.getContextPath() %>/register" method="post" class="container px-5">
      <%
          String errorMessage;
           errorMessage = request.getAttribute("errorMessage") != null ? (String) request.getAttribute("errorMessage") : "";
      %>
      <p class="text-danger text-center"><%= errorMessage %></p>
      <label class="mt-5" for="email">Email</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">

      <label class="mt-3" for="firstname">First Name</label>
      <input type="text" class="form-control" id="firstname" placeholder="Enter first name" name="firstname">

      <label class="mt-3" for="lastname">Last Name</label>
      <input type="text" class="form-control" id="lastname" placeholder="Enter last name" name="lastname">

      <label class="mt-3" for="username">User Name</label>
      <input type="text" class="form-control" id="username" placeholder="Enter user name" name="username">

      <label class="mt-3" for="contact">Contact Number</label>
      <input type="tel" class="form-control" id="contact" placeholder="Enter contact number" name="contact">

      <label class="mt-3" for="password">Password</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">

      <label class="mt-3" for="conf-password">Confirm Password</label>
      <input type="password" class="form-control" id="conf-password" placeholder="Repeat password" name="conf-password">

      <input type="submit" class="btn btn-block btn-outline-secondary p-2 mt-3" value="Register" />
  </form>
 </div>
</body>
</html>