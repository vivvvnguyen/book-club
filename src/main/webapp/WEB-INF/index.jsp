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

<title>Book Club Login/Reg</title>
</head>
<body>
	<div class="container p-3 mx-auto">
		<div class="header p-3 text-center text-primary">
			<h1>Book Club</h1>
			<h3>A place for friends to share thoughts on books!</h3>
		</div>
		<div class="body d-flex p-3 mx-auto">
			<div class="col p-5">
				<h2>Register</h2>
				<form:form action="/users/register" method="POST" modelAttribute="newUser">
				<form:errors class="error text-danger" path="firstName"/>
				<div class="form-floating mb-3">
					<form:input path="firstName" class="form-control" id="fNameInput" />
					<form:label path="firstName" for="fNameInput" class="form-label">First Name: </form:label>
				</div>
				<form:errors class="error text-danger" path="lastName"/>
				<div class="form-floating mb-3">
					<form:input path="lastName" class="form-control" id="lNameInput" />
					<form:label path="lastName" for="lNameInput" class="form-label">Last Name: </form:label>
				</div>
				<form:errors class="error text-danger" path="email"/>
				<div class="form-floating mb-3">
					<form:input path="email" class="form-control" id="emailInput" type="email"/>
					<form:label path="email" for="emailInput" class="form-label">Email: </form:label>
				</div>
				<form:errors class="error text-danger" path="password"/>
				<div class="form-floating mb-3">
					<form:input path="password" class="form-control" id="passInput" type="password"/>
					<form:label path="password" for="passInput" class="form-label">Password: </form:label>
				</div>
				<form:errors class="error text-danger" path="confirm"/>
				<div class="form-floating mb-3">
					<form:input path="confirm" class="form-control" id="confirmInput" type="password"/>
					<form:label path="confirm" for="confirmInput" class="form-label">Confirm Password: </form:label>
				</div>
				<input type=submit value="Register" class="btn btn-primary">
			</form:form>
			</div>
			<div class="col p-5">
				<h2>Login</h2>
				<form:form action="/users/login" method="POST" modelAttribute="loginUser">
				<form:errors class="error text-danger" path="email"/>
				<div class="form-floating mb-3">
					<form:input path="email" class="form-control" id="emailInput" type="email"/>
					<form:label path="email" for="emailInput" class="form-label">Email: </form:label>
				</div>
				<form:errors class="error text-danger" path="password"/>
				<div class="form-floating mb-3">
					<form:input path="password" class="form-control" id="passInput" type="password"/>
					<form:label path="password" for="passInput" class="form-label">Password: </form:label>
				</div>
				<input type=submit value="Login" class="btn btn-primary">
			</form:form>
			</div>
		</div>
	</div>
</body>
</html>