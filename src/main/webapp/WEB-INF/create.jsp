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

<title>Create Book Page</title>
</head>
<body>
	<div class="container p-3 mx-auto">
		<div class="header col p-3">
			<div class="row justify-content-between align-items-center ">
				<div class="col text-primary text-start">
					<h1 >Add a Book to Your Shelf!</h1>
				</div>
				<div class="col text-end">
					<a href="/users/home" class="btn btn-primary">Back to Home</a>
				</div>
			</div>
		</div>
		<div class="body col p-3">
			<div class="col">
				<form:form action="/books/create" method="POST" modelAttribute="newBook">
				<form:errors class="error text-danger" path="title"/>
				<div class="form-floating mb-3">
					<form:input path="title" class="form-control" id="titleInput" />
					<form:label path="title" for="titleInput" class="form-label">Title: </form:label>
				</div>
				<form:errors class="error text-danger" path="author"/>
				<div class="form-floating mb-3">
					<form:input path="author" class="form-control" id="authorInput" />
					<form:label path="author" for="authorInput" class="form-label">Author: </form:label>
				</div>
				<form:errors class="error text-danger" path="thoughts"/>
				<div class="form-floating mb-3">
					<form:input path="thoughts" class="form-control" id="thoughtsInput" />
					<form:label path="thoughts" for="thoughtsInput" class="form-label">Thoughts: </form:label>
				</div>
				<input type=submit value="Submit" class="btn btn-primary">
			</form:form>
			</div>
		</div>
	</div>
</body>
</html>