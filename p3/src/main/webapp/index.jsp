<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.reserve.handlers.ReservaHandler" %>
<%@page import="es.uco.pw.business.user.handlers.UsuarioHandler" %>
<%@page import="es.uco.pw.business.user.models.Usuario" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.LocalDateTime" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">

  <body>
 
  	<div class="form-style-6">
  	<%
	if(request.getParameter("ACL")!= null){
		%>
		<p class="cajaRoja"><%=request.getParameter("ACL")%></p>
		<%
	}else if (request.getAttribute("ACL")!=null){
		%>
		<p class="cajaRoja"><%=request.getAttribute("ACL")%></p>
		<%
	}
  	if(User.getEmail() == null || User.getRol() == null){
	  	%>
			<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("registerController")%>">Sign in</a></p>
			<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("loginController")%>">Login</a></p>
	  	<%
  	}else if(User.getRol().equals("ADMIN")){
  		String adminMenu=application.getInitParameter("adminMenuController");
  		%>
  		<jsp:forward page="<%=adminMenu%>"/>
  		<%
  	}else if(User.getRol().equals("USER")){
  		String userMenu=application.getInitParameter("userMenuController");
  		%>
  		<jsp:forward page="<%=userMenu%>"/>
  		<%
  	}
  		%>
  	</div>

  </body>
</html>