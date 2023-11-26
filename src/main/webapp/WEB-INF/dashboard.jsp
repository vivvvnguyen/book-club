<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css">
	<script type="text/javascript" src="/js/app.js"></script>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Book Club Dashboard</title>
</head>
<body>
	<div class="container">
		<div class="header col p-3">
			<div class="row justify-content-between align-items-center ">
				<div class="col text-start">
					<h1 >Welcome, <c:out value="${currentUser.firstName}"></c:out></h1>
					<h3>Books from everyone's shelves:</h3>
				</div>
				<div class="col text-end">
					<a href="/users/logout" class="btn btn-primary">Logout</a>
					<a href="/books/new" class="btn btn-success">Add to my Shelf!</a>
				</div>
			</div>
		</div>
		<div class="body col p-3">
			<table class="table table-striped table-bordered border-info" >
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Title</th>
						<th scope="col">Author Name</th>
						<th scope="col">Posted By</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="oneBook" items="${allBooks}">
						<tr>
							<td><c:out value="${oneBook.id}"></c:out></td>
							<td>
								<a href="/books/${oneBook.id}">
									<c:out value="${oneBook.title}"></c:out>
								</a>
							</td>
							<td><c:out value="${oneBook.author}"></c:out></td>
							<td><c:out value="${oneBook.creator.firstName}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>