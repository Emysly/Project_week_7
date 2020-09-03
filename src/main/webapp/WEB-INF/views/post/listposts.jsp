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
<div class="alert alert-success alert-dismissible fade show text-center" role="alert">
	<strong>User login successful</strong>
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<div class="container mt-5">

	<div>
		<h1>List Posts</h1>
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/add-post" class="btn btn-outline-success m-3"><i class="fas fa-plus"></i> Add Post</a>
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
			<!--  fetch posts -->
			<c:forEach var="post" items="${listPosts}">

				<!--  set up a link to update a post -->
				<c:url var="updateLink" value="GetServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<!--  set up a link to view a post -->
				<c:url var="viewLink" value="GetServlet">
					<c:param name="command" value="VIEW" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<!--  set up a link to delete a post -->
				<c:url var="deleteLink" value="GetServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<!--  set up a link to like a post -->
				<c:url var="likeLink" value="GetServlet">
					<c:param name="command" value="LIKE" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<!--  set up a link to dislike a post -->
				<c:url var="dislikeLink" value="GetServlet">
					<c:param name="command" value="UNLIKE" />
					<c:param name="postId" value="${post.id}" />
				</c:url>

				<tr>
					<td>${post.id}</td>
					<td>${post.title}</td>
					<td>${post.username}</td>
					<td>
						<a href="${updateLink}" data-toggle="tooltip" data-placement="top" title="Update"><i class="fas fa-edit"></i></a>
						&nbsp;&nbsp;&nbsp;
						<a href="${viewLink}" data-toggle="tooltip" data-placement="top" title="View"><i class="fas fa-eye text-secondary"></i></a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${deleteLink}"
						   onclick="if (!(confirm('Are you sure you want to delete this post?'))) return false">
							<i class="fas fa-minus-circle text-danger"  data-toggle="tooltip" data-placement="top" title="Delete"></i></a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a data-toggle="tooltip" data-placement="top" title="Like" href="${likeLink}"><i class="far fa-thumbs-up">${post.likePost}</i></a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a data-toggle="tooltip" data-placement="top" title="Unlike" href="${dislikeLink}"><i class="far fa-thumbs-down">${post.dislikePost}</i></a>
<%--						<button type="button" class="like btn btn-sm btn-secondary" data-toggle="tooltip" data-placement="top" title="Like" name="like" onclick="like()">--%>
<%--							Like--%>
<%--						</button>--%>
<%--						<button type="button" class="dislike btn btn-sm btn-secondary" data-toggle="tooltip" data-placement="top" title="Dislike" name="dislike" onclick="dislike()">--%>
<%--							Dislike--%>
<%--						</button>--%>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
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