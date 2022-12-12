<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Control Access</title>
</head>
<body>
	<%
	String returnPath = application.getInitParameter("index");

	if(User.getRol() != null ){
		%>
		<jsp:forward page="<%= returnPath %>">
		  <jsp:param name="ACL" value="Not allowed to go there" />
		</jsp:forward>
		<%
	}
	%>
</body>
</html>