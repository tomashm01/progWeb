<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@page import="es.uco.pw.business.user.models.Usuario" %>
<%@page import="es.uco.pw.business.user.handlers.UsuarioHandler" %>
<%@page import="es.uco.pw.business.reserve.handlers.ReservaHandler" %>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cargando...</title>
</head>
<body>

<%
String adminMenuViewPath = application.getInitParameter("adminMenuView");

ArrayList<Usuario> array =  UsuarioHandler.getInstance().getAllUsers();
ArrayList<Usuario> users = new ArrayList<Usuario>();
ArrayList<Integer> reservas = new ArrayList<Integer>();
for(Usuario it : array){
	if(it.getRol().equals("USER")){
    	Integer numReservas = ReservaHandler.getInstance().completedReservationsByUser(it.getEmail());
		users.add(it);
		reservas.add(numReservas);
	}
}
request.setAttribute("users",users);
request.setAttribute("reservas",reservas);

%>
<jsp:forward page="<%=adminMenuViewPath%>"/>

</body>
</html>