<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.user.handlers.UsuarioHandler" %>
<%@ page import="es.uco.pw.business.user.models.Usuario" %>
<%@ page import="java.time.LocalDate" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loading...</title>
</head>
<body>
	<%
	String modifyUserViewPath = application.getInitParameter("modifyUserView");
	String indexViewPath = application.getInitParameter("index");
	
	String email = User.getEmail();
	String rol = User.getRol();
	LocalDate inscripcion = User.getFechaIncripcion(); 
	
	String password = request.getParameter("password");
	String fechaNacimiento = request.getParameter("date");;
	String nombreCompleto = request.getParameter("nombrecompleto");
	
	if( password == null && fechaNacimiento == null && nombreCompleto == null){
	%>
		<jsp:forward page="<%=modifyUserViewPath%>" />
	<%
	}
	
	if(password == null){
		password= User.getPassword();
	}
	if(fechaNacimiento == null){
		fechaNacimiento = User.getFechaNacimiento().toString();
	}
	if(nombreCompleto == null){
		nombreCompleto = User.getNombreCompleto();
	}

	LocalDate nacimiento = LocalDate.parse(fechaNacimiento);
	
	Usuario modify = new Usuario(nombreCompleto,nacimiento,inscripcion,email,password,rol);
	if( ! UsuarioHandler.getInstance().editUser(modify)){
	%>
		<jsp:forward page="<%=modifyUserViewPath%>">
		    <jsp:param name="ErrorModificacion" value="true" />
		</jsp:forward>
	<%
	}else{
	%>
		<jsp:setProperty property="fechaNacimiento" value="<%=nacimiento%>"		name="User"/>
		<jsp:setProperty property="fechaIncripcion" value="<%=inscripcion%>" 	name="User"/>
		<jsp:setProperty property="nombreCompleto" 	value="<%=nombreCompleto%>" name="User"/>
		<jsp:setProperty property="password" 		value="<%=password%>" 		name="User"/>
		<jsp:setProperty property="antiguedad" 		value="<%=modify.antiquity()%>" name="User"/>
		<jsp:setProperty property="mayorEdad" 		value="<%=modify.isMayorEdad()%>" name="User"/>
		
		<jsp:forward page="<%=indexViewPath%>" />
	<%
	}
	%>
</body>