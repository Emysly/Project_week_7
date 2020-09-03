<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>List Comments</title>
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

	<div>
		<h1>List Comments</h1>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/add-comment" class="btn btn-outline-success m-3"><i class="fas fa-plus"></i>Add Comment</a>
		<a href="${pageContext.request.contextPath}/list-comments" class="btn btn-outline-info m-3">List All Comments</a>
	<c:forEach var="comment" items="${listComments}">

		<c:url var="updateLink" value="CommentServlet">
			<c:param name="command" value="LOAD" />
			<c:param name="commentId" value="${comment.id}" />
		</c:url>

		<c:url var="likeLink" value="CommentServlet">
			<c:param name="command" value="LIKE" />
			<c:param name="commentId" value="${comment.id}" />
		</c:url>

		<c:url var="unlikeLink" value="CommentServlet">
			<c:param name="command" value="UNLIKE" />
			<c:param name="commentId" value="${comment.id}" />
		</c:url>

		<!--  set up a link to view a post -->
		<c:url var="viewLink" value="GetServlet">
			<c:param name="command" value="VIEW" />
			<c:param name="postId" value="${comment.id}" />
		</c:url>

		<!--  set up a link to delete a comment -->
		<c:url var="deleteLink" value="CommentServlet">
			<c:param name="command" value="DELETE" />
			<c:param name="commentId" value="${comment.id}" />
		</c:url>

		<h4 class="mt-5">${comment.message}</h4>
		<div class="d-flex justify-content-between">
			<span>commented by: <span class="text-info"> ${comment.username}</span></span>
			<a href="${updateLink}" data-toggle="tooltip" data-placement="top" title="Update"><i class="fas fa-edit"></i></a>
			<a href="${deleteLink}"
			   onclick="if (!(confirm('Are you sure you want to delete this comment?'))) return false">
				<i class="fas fa-minus-circle text-danger"  data-toggle="tooltip" data-placement="top" title="Delete"></i></a>
			<a href="${likeLink}" data-toggle="tooltip" data-placement="top" title="Like"><i class="fas fa-thumbs-up">${comment.likePost}</i></a>
			<a href="${unlikeLink}" data-toggle="tooltip" data-placement="top" title="Unlike"><i class="fas fa-thumbs-down">${comment.dislikePost}</i></a>
		</div>
	</c:forEach>

	</div>
	<p class="mt-5">
		<a href="${viewLink}">Back to View</a>
	</p>
</div>


<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
    function preventBack(){
        window.history.forward();
    }

    setTimeout("preventBack()", 0);

    window.onunload=function(){
        null
    };


</script>
</body>
</html>