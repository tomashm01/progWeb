<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.reserve.handlers.ReservaHandler" %>
<%@page import="es.uco.pw.business.user.handlers.UsuarioHandler" %>
<%@page import="es.uco.pw.business.user.models.Usuario" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>

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

  	if(User.getEmail() == null || User.getRol() == null){
	  	%>
			<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("registerController")%>">Sign in</a></p>
			<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("loginController")%>">Login</a></p>
			<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("pruebasController")%>">Pruebas</a></p>
	  	<%
  	}else if(User.getRol().equals("ADMIN")){
		%>
		<table>
		  <thead>
		    <tr>
		      <th>Full Name</th>
		      <th>Antiquity</th>
		      <th>Completed Reservations</th>
		    </tr>
		  </thead>
		  <tbody>
		    <% for( Usuario it : UsuarioHandler.getInstance().getAllUsers()){
		        if(it.getRol().equals("USER")){
		          Integer reservas = ReservaHandler.getInstance().completedReservationsByUser(it.getEmail());
		    %>
		          <tr>
		            <td><%=it.getFullName()%></td>
		            <td><%=it.antiquity()%></td>
		            <td><%=reservas%></td>
		          </tr>
		    <% 
		   	 	}
		      } %>
		  </tbody>
		</table>

			<p><a href="${pageContext.request.contextPath}/addKart">Dar de alta Karts</a></p>
			<p><a href="${pageContext.request.contextPath}/addPista">Dar de alta Pista</a></p>
			<p><a href="${pageContext.request.contextPath}/modifyKart">Modificar Karts</a></p>
			<p><a href="${pageContext.request.contextPath}/modifyPista">Modificar Pista</a></p>
			<p><a href="${pageContext.request.contextPath}/deleteReserve">Eliminar reservas</a></p>
			<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("logoutController")%>">logout</a></p>
	  		<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("modifyUserController")%>">ModificarUsuario</a></p>
	  	<%
  	}else if(User.getRol().equals("USER")){
	  		String fecha = LocalDate.now().toString();
	  %>
	  		<p>Bienvenido <jsp:getProperty property="nombreCompleto" name="User"/></p>
	  		<p>Hoy es <%=fecha%></p>
	  		<p>Tu antiguedad es: <jsp:getProperty property="antiguedad" name="User"/> años</p> 
	  	<%
		  	if( ReservaHandler.getInstance().getNextReserveByUser(User.getEmail()) != null){
		  		 String reserva = ReservaHandler.getInstance().getNextReserveByUser(User.getEmail()).getDate().toString();
		%>
		  		<p>Tu siguiente reserva es el dia :<%=reserva %> </p>
		<%
		  	}
	  	%>	
	  		<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("addReserveView")%>">Nueva reserva</a></p>
	  		<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("modifyReserve")%>">Modificar reserva</a></p>
	  		<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("reserveView")%>">Ver reserva</a></p>
	  		<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("logoutController")%>">logout</a></p>
	  		<p><a href="${pageContext.request.contextPath}<%=application.getInitParameter("modifyUserController")%>">ModificarUsuario</a></p>
	  	<% 	
  	}
  		%>
  	</div>
  </body>

</html>