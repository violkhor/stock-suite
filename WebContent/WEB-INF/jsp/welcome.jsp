<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="resources/css/default.css" rel="stylesheet" type="text/css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>

<body>
	<div style="width: 100%;" align="center">
		<h2>Welcome to StockSuite v1.0</h2>

	</div>
	<div style="top-padding: 20px" align="center">
		<!-- 
		<h2>File Upload</h2>
		<form action="<c:url value="/upload" />" method="GET">
			<input type="submit" name="action" value="upload" />
		</form>
		<div style="top-padding: 20px" align="center">
			<h2>Yahoo!</h2>
			<form action="<c:url value="/yahoo" />" method="GET">
				<input type="submit" name="action" value="recent" />
			</form>
			<form action="<c:url value="/yahoo" />" method="GET">
				<input type="submit" name="action" value="historical" />
			</form>
			<form action="<c:url value="/yahoo" />" method="GET">
				<input type="submit" name="action" value="updateExchange" />
			</form>
		-->

	</div>
	<div id="container" align="center">
		<span><a href="yahoo"><img src="resources/img/yahoo.jpg" /></a></span>
		<span style="padding-left: 80px"><a href="google"><img
				src="resources/img/google.jpg" /></a></span>
	</div>

</body>

</html>