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

<title>Show Book Page</title>
</head>
<body>
	<div class="container p-3 mx-auto">
		<div class="header col p-3">
			<div class="row justify-content-between align-items-center ">
				<div class="col text-primary text-start">
					<h1>${book.title}</h1>
				</div>
				<div class="col text-end">
					<a href="/users/home" class="btn btn-primary">Back to Home</a>
				</div>
			</div>
		</div>
		<div class="body col p-3">
			<c:choose>
				<c:when test="${book.creator.id == currentUser}">
					<p>You read ${book.title} by ${book.author}</p>
					<h3>Here are your thoughts:</h3>
				</c:when>
				<c:otherwise>
					<p>${book.creator.firstName} read ${book.title} by ${book.author}</p>
					<h3>Here are ${book.creator.firstName}'s thoughts:</h3>
				</c:otherwise>
			</c:choose>
			<p>${book.thoughts}</p>
<%-- 			<p>${book.creator.id}</p> --%>
<%-- 			<p>${currentUser}</p> --%>
			<c:if test="${book.creator.id == currentUser}">
				<div class="d-flex">
					<a href="/books/edit/${book.id}" class="btn btn-success">Edit</a>
<%-- 					<a href="/books/delete/${book.id}" class="btn btn-warning">Delete</a> --%>
					<form action="/books/delete/${book.id}" method="POST">
						<input type="hidden" name="_method" value="delete">
						<input type="submit" value="Delete" class="btn btn-warning ms-2">
					</form>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>