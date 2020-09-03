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

		<%--		<c:url var="viewLink" value="CommentServlet">--%>
		<%--			<c:param name="command" value="VIEW" />--%>
		<%--			<c:param name="commentId" value="${comment.id}" />--%>
		<%--		</c:url>--%>

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
<%--			<a data-toggle="tooltip" data-placement="top" title="Like" class="like" onclick="like()"><i class="far fa-thumbs-up"><span class="count"></span></i></a>--%>
<%--			<a data-toggle="tooltip" data-placement="top" title="Unlike" class="dislike" onclick="dislike()"><i class="far fa-thumbs-down"><span class="discount"></span></i></a>--%>
		</div>
	</c:forEach>

	</div>
	<p class="mt-5">
		<a href="${pageContext.request.contextPath}/list-views">Back to View</a>
	</p>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
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



    let count = 1;
    let discount = 1;

    document.querySelector(".like").style.cursor = "pointer";
    document.querySelector(".dislike").style.cursor = "pointer";
    function like() {
        document.querySelector(".like").style.cursor = "pointer";
        document.querySelector(".dislike").style.color = "black";
        document.querySelector(".like").style.color = "red";
        document.querySelector(".count").textContent = count++;
    }

    function dislike() {
        document.querySelector(".dislike").style.cursor = "pointer";
        document.querySelector(".like").style.color = "black";
        document.querySelector(".dislike").style.color = "red";
        document.querySelector(".discount").textContent = discount++;
    }

</script>
</body>
</html>