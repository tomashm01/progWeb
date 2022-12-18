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
if(User.getRol() != null ){
	String returnPath = application.getInitParameter("adminMenuController");;
	if( User.getRol().equals("USER")){
		returnPath = application.getInitParameter("userMenuController");
	}
	%>
	<jsp:forward page="<%=returnPath%>">
	  <jsp:param name="ACL" value="Not allowed to go there" />
	</jsp:forward>
<%}%>
</body>
</html>


