<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form method="post" action="getCompany">

		<table>

			<tr>
				<td><form:label path="name">Name: </form:label></td>
				<td><form:input path="name" /></td>
				<td>
					<ul>
						<form:select path="symbol" items="${symbolsMap}"></form:select>
					</ul>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Get Company" /></td>
			</tr>
		</table>

	</form:form>


</body>
</html>