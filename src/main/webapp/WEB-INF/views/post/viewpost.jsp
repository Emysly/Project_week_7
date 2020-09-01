<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emysilva
  Date: 30/08/2020
  Time: 3:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>${post.title}</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
<div class="container">
	<h1 class="mt-5 text-center">${post.title}</h1>
	<div class="d-flex justify-content-between">
	<span>posted by: <span class="text-info"> ${post.username}</span></span>
<%--		<input type="button" value="Add Comment"--%>
<%--		       data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo"--%>
<%--		       class="btn btn-outline-success ml-5"--%>
<%--		/>--%>
		<button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Add Comment</button>
	</div>
	<hr>
	<br>
	<h4>${post.message}</h4>
	<c:forEach var="comment" items="${comments}">

		<c:url var="updateLink" value="CommentServlet">
			<c:param name="command" value="LOAD" />
			<c:param name="commentId" value="${comment.id}" />
		</c:url>

<%--		<c:url var="viewLink" value="CommentServlet">--%>
<%--			<c:param name="command" value="VIEW" />--%>
<%--			<c:param name="commentId" value="${comment.id}" />--%>
<%--		</c:url>--%>

		<!--  set up a link to delete a comment -->
		<c:url var="deleteLink" value="CommentServlet">
			<c:param name="command" value="DELETE" />
			<c:param name="commentId" value="${comment.id}" />
		</c:url>

		<h4>${comment.message}</h4>
		<div class="d-flex justify-content-between">
			<span>commented by: <span class="text-info"> ${comment.username}</span></span>
			<a href="${updateLink}" class="btn btn-outline-success ml-5" data-toggle="modal"
			   data-target="#exampleModal1" data-whatever="@mdo">Edit</a>
			<a href="${deleteLink}" class="btn btn-outline-danger ml-5">Delete</a>
		</div>
	</c:forEach>
	<p class="m-5">
		<a href="${pageContext.request.contextPath}/list">Back to List</a>
	</p>
</div>




<%-- add modal--%>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New comment</h5>
			</div>
			<div class="modal-body">
				<form action="CommentServlet" method="get">
					<input type="hidden" name="command" value="ADD" />
					<div class="form-group">
						<label for="email" class="col-form-label">Email:</label>
						<input type="email" class="form-control" id="email" name="email" />
					</div>
					<div class="form-group">
						<label for="username" class="col-form-label">Username:</label>
						<input type="text" class="form-control" id="username" name="username" />
					</div>
					<div class="form-group">
						<label for="message-text" class="col-form-label">Message:</label>
						<textarea class="form-control" id="message-text" name="message"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
						<input type="submit" class="btn btn-primary" value="Send message" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<%-- update modal--%>

<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel1">Update comment</h5>
			</div>
			<div class="modal-body">
				<form action="CommentServlet" method="get">
					<input type="hidden" name="command" value="UPDATE" />
					<input type="hidden" name="commentId" value="${comment.id}"/>
					<div class="form-group">
						<label for="update-email" class="col-form-label">Email:</label>
						<input type="email" class="form-control" id="update-email" name="email" value="${comment.email}"/>
					</div>
					<div class="form-group">
						<label for="update-username" class="col-form-label">Username:</label>
						<input type="text" class="form-control" id="update-username" name="username" value="${comment.username}"/>
					</div>
					<div class="form-group">
						<label for="update-message-text" class="col-form-label">Message:</label>
						<textarea class="form-control" id="update-message-text" name="message">${comment.message}</textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
						<input type="submit" class="btn btn-primary" value="Send message" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script>

    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
    })

    $('#exampleModal1').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
    })
</script>
</body>
</html>
