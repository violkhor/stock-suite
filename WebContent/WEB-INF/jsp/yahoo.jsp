<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="resources/css/default.css" rel="stylesheet" type="text/css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Yahoo Request Page</title>
</head>

<body>
	<h2>Welcome to the Yahoo Request Page</h2>
	<div>
		<c:url value="refreshYahoo" var="url" />
		<a href="<c:out value='${url}'/>">Refresh</a>
		<p>
			Last updated: <b>${lastUpdateTime}</b>
		</p>
	</div>
	<div align="center">
		<table class="inlineTable">
			<tr>
				<th colspan="2" align="center">TSX</th>
			</tr>
			<tr>

				<th>Symbol</th>
				<th>% Change</th>
			</tr>
			<c:forEach items="${tsxComp}" var="p">
				<tr>

					<td><c:out value="${p.symbol}" /></td>
					<td><c:out value="${p.changeFromYearLowPercent}" /></td>
				</tr>
			</c:forEach>


		</table>


	</div>
</body>

</html>