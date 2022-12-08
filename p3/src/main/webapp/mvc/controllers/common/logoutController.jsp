<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout..</title>
</head>
<body>
<%
String indexPath = application.getInitParameter("index");
request.getSession().removeAttribute("User");
%>
<jsp:forward page="<%=indexPath%>" />

</body>
</html>