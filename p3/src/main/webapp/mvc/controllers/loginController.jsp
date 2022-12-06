<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.user.handlers.UsuarioHandler" %>
<%@ page import="es.uco.pw.business.user.models.Usuario" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loading...</title>
</head>
<body>
	<%
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	
	if(email == null){
	%>	
		<jsp:forward page="../views/login.jsp" />
	<%
	}
	else if( ! UsuarioHandler.getInstance().checkout(email,password)){
	%>
		<jsp:forward page="../views/login.jsp">
		    <jsp:param name="Errorlogin" value="true" />
		</jsp:forward>
	<%
	}else{
		Usuario login = UsuarioHandler.getInstance().getUserByEmail(email);
	%>
		<jsp:setProperty property="email" 			value="<%=email%>" 						name="User"/>
		<jsp:setProperty property="fechaNacimiento" value="<%=login.getBirthdayDate()%>"	name="User"/>
		<jsp:setProperty property="fechaIncripcion" value="<%=login.getInscriptionDate()%>" name="User"/>
		<jsp:setProperty property="nombreCompleto" 	value="<%=login.getFullName()%>" 		name="User"/>
		<jsp:setProperty property="rol" 			value="<%=login.getRol()%>" 			name="User"/>
		<jsp:setProperty property="password" 		value="<%=password%>" 					name="User"/>
		<jsp:setProperty property="antiguedad" 		value="<%=login.antiquity()%>" 			name="User"/>
		<jsp:setProperty property="mayorEdad" 	value="<%=login.isMayorEdad()%>" 		name="User"/>
		
		<jsp:forward page="../../index.jsp" />
	<%
	}
	%>
</body>
</html>